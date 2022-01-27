package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.dao.ItemDao;
import ru.otus.spring.domain.Item;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Сервисный слой для работы с CSV-файлами ItemServiceImpl должен ")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemServiceImplTest {

    @MockBean
    private ItemDao itemDao;

    @Autowired
    private ItemServiceImpl itemService;

    @Test
    @DisplayName("получать список всех вопросов из файла")
    public void shouldFindAll() {
        List<Item> expectedList = getTestItems();

        when(itemDao.findAll()).thenReturn(expectedList);
        List<Item> actualList = itemService.findAll();

        assertThat(actualList).hasSize(3).usingRecursiveComparison().isEqualTo(expectedList);
        verify(itemDao, times(1)).findAll();
    }

    @Test
    @DisplayName("получать вопрос по имени")
    public void shouldFindByQuestion() {
        List<Item> items = getTestItems();

        when(itemDao.findAll()).thenReturn(items);
        Optional<Item> item = itemService.findByQuestion("Question3?");

        assertThat(item).isPresent().get().matches(i -> i.getQuestion().equals("Question3?") && i.getAnswer().equals("Answer3"));
        verify(itemDao, times(1)).findAll();
    }

    private List<Item> getTestItems() {
        return List.of(
                new Item("Question1?", "Answer1"),
                new Item("Question2?", "Answer2"),
                new Item("Question3?", "Answer3"));
    }

}