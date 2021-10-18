package com.example.kairo.listoflistsback.framework.exception;

import lombok.NoArgsConstructor;

import static java.util.stream.Stream.of;

@NoArgsConstructor
public class CustomException extends RuntimeException {

    public CustomException(String message, String... details) {
        super(message, getBody(details), true, true);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    private static Exception getBody(String[] details) {
        var body = of(details)
                .map(s -> s + "\n")
                .reduce(String::concat)
                .orElse("Erro fora do Padrão");

        return new Exception(body);
    }
}
