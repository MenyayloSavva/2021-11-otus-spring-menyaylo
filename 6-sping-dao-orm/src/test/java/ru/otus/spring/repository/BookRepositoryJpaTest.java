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

@DisplayName("Репозиторий на основе Jpa для работы с книгами ")
@DataJpaTest
@Import(BookRepositoryJpa.class)
public class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать информацию о нужной книге по id")
    @Test
    void shouldFindExpectedBookById() {
        Optional<Book> actualBook = repository.findById(1);
        Book expectedBook = em.find(Book.class, 1);
        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("должен загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnCorrectBookListWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        List<Book> books = repository.findAll();
        assertThat(books).isNotNull().hasSize(3)
                .allMatch(b -> !b.getName().equals(""))
                .allMatch(b -> b.getGenre() != null)
                .allMatch(b -> b.getAuthor() != null);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(3L);
    }

    @DisplayName("должен корректно сохранять всю информацию о книге")
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

        List<BookComment> comments = Collections.singletonList(BookComment.builder()
                .id(0)
                .text("«Война и мир» — одно из высших достижений художественного гения Толстого. " +
                        "Книга потребовала от писателя громадных усилий.")
                .book(warAndPeace)
                .build());

        warAndPeace = warAndPeace.toBuilder()
                .id(0)
                .name("Война и мир")
                .yearOfPublication("1867")
                .author(author)
                .genre(genre)
                .comments(comments)
                .build();

        repository.save(warAndPeace);
        assertThat(warAndPeace.getId()).isGreaterThan(0);

//        val actualStudent = em.find(OtusStudent.class, vasya.getId());
//        assertThat(actualStudent).isNotNull().matches(s -> !s.getName().equals(""))
//                .matches(s -> s.getCourses() != null && s.getCourses().size() > 0 && s.getCourses().get(0).getId() > 0)
//                .matches(s -> s.getAvatar() != null)
//                .matches(s -> s.getEmails() != null && s.getEmails().size() > 0);
    }

    @DisplayName("должен загружать информацию о нужной книге по имени")
    @Test
    void shouldFindExpectedBookByName() {
        Book firstBook = em.find(Book.class, 2);
        List<Book> books = repository.findByName("Евгений Онегин");
        assertThat(books).containsOnlyOnce(firstBook);
    }

    @DisplayName("должен изменять имя книги по её id")
    @Test
    void shouldUpdateBookNameById() {
        Book firstBook = em.find(Book.class, 1);
        String oldName = firstBook.getName();
        em.detach(firstBook);

        repository.updateNameById(1, "Вино из одуванчиков");
        Book updatedBook = em.find(Book.class, 1);

        assertThat(updatedBook.getName()).isNotEqualTo(oldName).isEqualTo("Вино из одуванчиков");
    }

    @DisplayName("должен удалять заданную книгу по id")
    @Test
    void shouldDeleteBookById() {
        Book thirdBook = em.find(Book.class, 3);
        assertThat(thirdBook).isNotNull();
        em.detach(thirdBook);

        repository.deleteById(3);
        Book deletedBook = em.find(Book.class, 3);
        assertThat(deletedBook).isNull();
    }
}