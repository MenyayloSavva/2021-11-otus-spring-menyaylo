package ru.otus.srping.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.ItemDao;
import ru.otus.spring.domain.Item;
import ru.otus.spring.service.ItemServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Сервисный слой для работы с CSV-файлами ItemServiceImpl должен ")
@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {

    @Mock
    private ItemDao itemDao;

    @InjectMocks
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