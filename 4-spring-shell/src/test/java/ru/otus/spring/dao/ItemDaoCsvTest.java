package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.domain.Item;
import ru.otus.spring.input.FileReaderImpl;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Слой для работы с CSV-файлами ItemDaoSimple должен ")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemDaoCsvTest {

    private static final String FILE_PATH = "/items-test.csv";

    @Autowired
    private ItemDaoCsv itemDao;

    @MockBean
    private FileReaderImpl fileReader;

    @Test
    @DisplayName("получать список всех вопросов из файла")
    void shouldFindAll() {
        List<Item> expectedList = List.of(
                new Item("Question1?", "Answer1"),
                new Item("Question2?", "Answer2"),
                new Item("Question3?", "Answer3"));

        when(fileReader.readData(anyString())).thenReturn(
                new InputStreamReader(getClass().getResourceAsStream(FILE_PATH), StandardCharsets.UTF_8));
        List<Item> actualList = itemDao.findAll();

        assertThat(actualList).hasSize(3).usingRecursiveComparison().isEqualTo(expectedList);
        verify(fileReader, times(1)).readData(anyString());
    }
}