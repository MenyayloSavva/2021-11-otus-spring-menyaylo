package ru.otus.spring.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "project")
public class ProjectConfigs {
    private String language;
    private List<Path> path;
    private Answer answer;

    @Data
    public static class Path {
        private String language;
        private String pathToCsv;
    }

    @Data
    public static class Answer {
        private int requiredAmount;
    }
}









