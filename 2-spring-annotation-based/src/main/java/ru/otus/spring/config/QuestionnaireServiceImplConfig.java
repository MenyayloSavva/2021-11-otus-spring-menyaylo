package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.ItemService;
import ru.otus.spring.service.QuestionnaireService;
import ru.otus.spring.service.QuestionnaireServiceImpl;

@Configuration
public class QuestionnaireServiceImplConfig {

    @Bean
    public QuestionnaireService questionnaireServiceImpl(ItemService itemService) {
        return new QuestionnaireServiceImpl(itemService);
    }
}
