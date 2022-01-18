package ru.otus.srping.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.ItemDaoSimple;
import ru.otus.spring.domain.Item;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Слой для работы с CSV-файлами ItemDaoSimple должен ")
@ExtendWith(MockitoExtension.class)
public class ItemDaoSimpleTest {

    private static final String PATH_TO_CSV = "/items-test.csv";

    @InjectMocks
    private ItemDaoSimple itemDao;

    @Test
    @DisplayName("получать список всех вопросов из файла")
    void shouldFindAll() {
        itemDao.setPathToCsv(PATH_TO_CSV);
        List<Item> expectedList = List.of(
                new Item("Question1?", "Answer1"),
                new Item("Question2?", "Answer2"),
                new Item("Question3?", "Answer3"));

        List<Item> actualList = itemDao.findAll();

        assertThat(actualList).hasSize(3).usingRecursiveComparison().isEqualTo(expectedList);
    }

}