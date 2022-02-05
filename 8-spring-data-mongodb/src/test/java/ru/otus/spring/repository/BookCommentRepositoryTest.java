package ru.otus.spring.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.spring.domain.BookComment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книжными комментариями ")
@DataMongoTest
public class BookCommentRepositoryTest {

    @Autowired
    private BookCommentRepository repository;

    @DisplayName("должен находить книжные комментарии по id книги")
    @Test
    void shouldReturnCommentsWithAllInfo() {
        BookComment expectedComment = repository.findById(3L).orElseThrow();

        List<BookComment> actualCommentList = repository.findByBook_Id(2L);

        assertThat(actualCommentList).isNotNull()
                .hasSize(1)
                .containsOnly(expectedComment);
    }
}