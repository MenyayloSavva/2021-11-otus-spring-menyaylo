package ru.otus.spring.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Locale;

@Configuration
public class LocaleConfigs {

    @PostConstruct
    public void setDefaultLocale() {
        Locale.setDefault(findLocaleByLanguageAndCountry("en", "US"));
    }

    @Bean
    @ConditionalOnProperty(prefix = "project", name = "language", havingValue = "default")
    public Locale getDefaultLocale() {
        return findLocaleByLanguageAndCountry("en", "US");
    }

    @Bean
    @ConditionalOnProperty(prefix = "project", name = "language", havingValue = "russian")
    public Locale getRussianLocale() {
        return findLocaleByLanguageAndCountry("ru", "RU");
    }

    private Locale findLocaleByLanguageAndCountry(String language, String country) {
        return Arrays.stream(Locale.getAvailableLocales())
                .filter(locale -> locale.getLanguage().equals(language))
                .filter(locale -> locale.getCountry().equals(country))
                .filter(locale -> locale.getVariant().isEmpty())
                .filter(locale -> locale.getScript().isEmpty())
                .findFirst().orElseThrow(RuntimeException::new);
    }
}
