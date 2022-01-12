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
import java.util.Collections;
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
        Optional<BookComment> actualComment = repository.findById(1);
        BookComment expectedComment = em.find(BookComment.class, 1);
        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("должен загружать список всех комментариев с полной информацией о них")
    @Test
    void shouldReturnCorrectCommentListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        List<BookComment> comments = repository.findAll();
        assertThat(comments).isNotNull().hasSize(4)
                .allMatch(bc -> !bc.getText().equals(""))
                .allMatch(bc -> bc.getBook() != null);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(3L);
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

        Book warAndPeace = Book.builder().build();

        BookComment comment = BookComment.builder()
                .id(0)
                .text("«Война и мир» — одно из высших достижений художественного гения Толстого. " +
                        "Книга потребовала от писателя громадных усилий.")
                .book(warAndPeace)
                .build();

        List<BookComment> comments = Collections.singletonList(comment);

        warAndPeace = warAndPeace.toBuilder()
                .id(0)
                .name("Война и мир")
                .yearOfPublication("1867")
                .author(author)
                .genre(genre)
                .comments(comments)
                .build();

        repository.save(comment);
        assertThat(warAndPeace.getId()).isGreaterThan(0);

//        val actualStudent = em.find(OtusStudent.class, vasya.getId());
//        assertThat(actualStudent).isNotNull().matches(s -> !s.getName().equals(""))
//                .matches(s -> s.getCourses() != null && s.getCourses().size() > 0 && s.getCourses().get(0).getId() > 0)
//                .matches(s -> s.getAvatar() != null)
//                .matches(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }

    @DisplayName("должен изменять текст комментария по id")
    @Test
    void shouldUpdateCommentTextById() {
        BookComment firstComment = em.find(BookComment.class, 1);
        String oldText = firstComment.getText();
        em.detach(firstComment);

        repository.updateTextById(1, "New Text");
        BookComment updatedComment = em.find(BookComment.class, 1);

        assertThat(updatedComment.getText()).isNotEqualTo(oldText).isEqualTo("New Text");
    }

    @DisplayName("должен удалять заданный комментарий по id")
    @Test
    void shouldDeleteBookById() {
        BookComment fourthComment = em.find(BookComment.class, 4);
        assertThat(fourthComment).isNotNull();
        em.detach(fourthComment);

        repository.deleteById(4);
        BookComment deletedComment = em.find(BookComment.class, 4);
        assertThat(deletedComment).isNull();
    }
}