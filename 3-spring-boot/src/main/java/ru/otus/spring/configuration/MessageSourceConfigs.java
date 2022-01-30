package ru.otus.spring.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class MessageSourceConfigs {

    private final Locale locale;

    @Bean
    public ResourceBundleMessageSourceLocalized messageSource(){
        ResourceBundleMessageSourceLocalized messageSource = new ResourceBundleMessageSourceLocalized();
        messageSource.setBasename("i18n/quiz");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCurrentLocale(locale);
        return messageSource;
    }
}
