package ru.otus.spring.configuration;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ResourceBundleMessageSourceLocalized extends ResourceBundleMessageSource {

    private Locale currentLocale;

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
}
