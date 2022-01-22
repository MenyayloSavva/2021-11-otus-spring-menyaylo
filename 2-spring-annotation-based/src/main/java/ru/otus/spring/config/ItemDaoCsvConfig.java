package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.ItemDao;
import ru.otus.spring.dao.ItemDaoCsv;
import ru.otus.spring.input.FileReader;

@Configuration
public class ItemDaoCsvConfig {

    @Bean
    public ItemDao itemDaoCsv(@Value("${paths.path_to_csv}") String pathToCsv, FileReader fileReader) {
        return new ItemDaoCsv(pathToCsv, fileReader);
    }
}
