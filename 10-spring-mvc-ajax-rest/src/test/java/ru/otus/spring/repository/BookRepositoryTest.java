package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.domain.Book;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @DisplayName("должен находить нужную книгу по имени")
    @Test
    void shouldFindBookByName() {
        Book expectedBook = repository.findById(3L).orElseThrow();

        List<Book> actualBookList = repository.findByName("Декабрьское утро");

        assertThat(actualBookList).isNotNull()
                .hasSize(1)
                .containsOnly(expectedBook);
    }
}