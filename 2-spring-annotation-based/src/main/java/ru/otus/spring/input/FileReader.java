package ru.otus.spring.input;

import java.io.InputStreamReader;

public interface FileReader {
    InputStreamReader readData(String filePath);
}
