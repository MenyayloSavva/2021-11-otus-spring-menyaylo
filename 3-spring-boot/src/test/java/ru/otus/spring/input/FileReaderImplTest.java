package ru.otus.spring.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс для чтения данных FileReaderImpl должен ")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FileReaderImplTest {

    private static final String FILE_PATH = "/items-test.csv";

    @Autowired
    private FileReaderImpl fileReader;

    @Test
    @DisplayName("читать данные и передавать InputStreamReader")
    void shouldReadData() {
        InputStreamReader reader = fileReader.readData(FILE_PATH);
        assertThat(reader).isNotNull();
    }

}