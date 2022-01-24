package ru.otus.spring.input;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class FileReaderImpl implements FileReader {

    public FileReaderImpl() {
    }

    @Override
    public InputStreamReader readData(String filePath) {
        InputStream in = getClass().getResourceAsStream(filePath);
        return new InputStreamReader(in, StandardCharsets.UTF_8);
    }
}
