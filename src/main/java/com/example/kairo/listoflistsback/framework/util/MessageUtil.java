package com.example.kairo.listoflistsback.framework.util;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

    private static MessageSource messageSource;

    public MessageUtil(MessageSource messageSource) {
        MessageUtil.messageSource = messageSource;
    }

    public static String get(String id) {
        return get(id, null);
    }

    public static String get(String id, Object... args) {
        if (messageSource == null) {
            return "";
        }

        try {
            return messageSource.getMessage(id, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return id;
        }
    }
}
