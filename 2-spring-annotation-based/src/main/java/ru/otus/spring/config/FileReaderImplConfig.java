package ru.otus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.input.FileReader;
import ru.otus.spring.input.FileReaderImpl;

@Configuration
public class FileReaderImplConfig {

    @Bean
    public FileReader fileReader() {
        return new FileReaderImpl();
    }
}
