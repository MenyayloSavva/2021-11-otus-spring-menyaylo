package ru.otus.spring.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книжными комментариями ")
@DataJpaTest
@Import(BookCommentRepositoryJpa.class)
public class BookCommentRepositoryJpaTest {

    @Autowired
    private BookCommentRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать информацию о нужном комментарии по id")
    @Test
    void shouldFindExpectedCommentById() {
        Optional<BookComment> actualComment = repository.findById(1L);
        BookComment expectedComment = em.find(BookComment.class, 1L);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("должен загружать список всех комментариев с полной информацией о них")
    @Test
    void shouldReturnCorrectCommentListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        List<BookComment> comments = repository.findAll();

        assertThat(comments).isNotNull().hasSize(5)
                .allMatch(bc -> !bc.getText().equals(""))
                .allMatch(bc -> bc.getBook() != null);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1L);
    }

    @DisplayName("должен корректно сохранять всю информацию о комментарии")
    @Test
    void shouldSaveAllBookInfo() {
        Author author = Author.builder()
                .id(0)
                .country("Russia")
                .birthDate(LocalDate.of(1828, 9, 9))
                .name("Лев Николаевич Толстой")
                .build();

        Genre genre = Genre.builder()
                .id(0)
                .name("Роман")
                .build();

        Book warAndPeace = Book.builder()
                .id(0)
                .name("Война и мир")
                .yearOfPublication("1867")
                .author(author)
                .genre(genre)
                .build();

        BookComment comment = BookComment.builder()
                .id(0)
                .text("«Война и мир» — одно из высших достижений художественного гения Толстого. " +
                        "Книга потребовала от писателя громадных усилий.")
                .book(warAndPeace)
                .build();

        repository.save(comment);
        assertThat(comment.getId()).isGreaterThan(0L);

        BookComment actualComment = em.find(BookComment.class, comment.getId());
        assertThat(actualComment).isNotNull().matches(bc -> !bc.getText().equals(""))
                .matches(bc -> bc.getBook() != null && !bc.getBook().getName().equals(""))
                .matches(bc -> bc.getBook().getAuthor().getId() > 0L)
                .matches(bc -> bc.getBook().getGenre().getId() > 0L);
    }

    @DisplayName("должен удалять заданный комментарий по id")
    @Test
    void shouldDeleteBookById() {
        BookComment fourthComment = em.find(BookComment.class, 4L);
        assertThat(fourthComment).isNotNull();
        em.detach(fourthComment);

        repository.deleteById(4L);
        BookComment deletedComment = em.find(BookComment.class, 4L);
        assertThat(deletedComment).isNull();
    }
}