package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.ItemDao;
import ru.otus.spring.service.ItemService;
import ru.otus.spring.service.ItemServiceImpl;

@Configuration
public class ItemServiceImplConfig {

    @Bean
    public ItemService itemServiceImpl(ItemDao itemDao) {
        return new ItemServiceImpl(itemDao);
    }
}
