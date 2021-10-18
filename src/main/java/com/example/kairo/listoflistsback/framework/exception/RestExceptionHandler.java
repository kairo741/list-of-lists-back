package com.example.kairo.listoflistsback.framework.exception;


import com.example.kairo.listoflistsback.framework.util.MessageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex);
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String defaultMessage = error.getDefaultMessage();

            if (defaultMessage != null && defaultMessage.contains("{") && defaultMessage.contains("}")) {
                Template template = new Template(defaultMessage);
                Map<String, String> map = new HashMap<>();
                for (String tag : template.getTags()) {
                    map.put(tag, MessageUtil.get(tag));
                }
                message.append(template.substituir(map)).append("<br>");
            } else {
                message.append(defaultMessage).append("<br>");
            }
        });
        CustomError customError = new CustomError(CustomError.Type.INFO, message.toString(), null);
        return handleExceptionInternal(ex, customError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    protected ResponseEntity<Object> handleError(AccessDeniedException ex, WebRequest request) {
        logger.error(ex);
        CustomError customError = new CustomError(CustomError.Type.WARNING, ex.getMessage(), ex);
        return handleExceptionInternal(ex, customError, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = InfoException.class)
    protected ResponseEntity<Object> handleError(InfoException ex, WebRequest request) {
        logger.error(ex);
        CustomError customError = new CustomError(CustomError.Type.INFO, ex.getMessage(), ex);
        return handleExceptionInternal(ex, customError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = WarningException.class)
    protected ResponseEntity<Object> handleError(WarningException ex, WebRequest request) {
        logger.error(ex);
        CustomError customError = new CustomError(CustomError.Type.WARNING, ex.getMessage(), ex);
        return handleExceptionInternal(ex, customError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleError(Exception ex, WebRequest request) {
        logger.error(ex);
        CustomError customError = new CustomError(CustomError.Type.ERROR, ex.getMessage(), ex);
        return handleExceptionInternal(ex, customError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}


