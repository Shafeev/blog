package ru.simple.blog.localization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageManager {
    private static MessageSource messageSource;

    public static String getMsg(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    public static String getMsg(String key, String... arg) {
        Locale locale = LocaleContextHolder.getLocale();
        Object[] args = new Object[arg.length];
        System.arraycopy(arg, 0, args, 0, arg.length);
        return messageSource.getMessage(key, args, locale);
    }

    @Autowired(required = true)
    public void setMessageSource(MessageSource messageSource) {
        MessageManager.messageSource = messageSource;
    }
}
