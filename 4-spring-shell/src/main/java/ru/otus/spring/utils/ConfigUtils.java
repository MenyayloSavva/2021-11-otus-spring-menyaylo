package ru.otus.spring.utils;

import lombok.experimental.UtilityClass;
import ru.otus.spring.configuration.ProjectConfigs;

@UtilityClass
public class ConfigUtils {

    public static String getPathToCsv(ProjectConfigs projectConfigs) {
        return projectConfigs.getPath().stream()
                .filter(path -> path.getLanguage().equals(projectConfigs.getLanguage()))
                .findFirst().orElseThrow(RuntimeException::new)
                .getPathToCsv();
    }
}
