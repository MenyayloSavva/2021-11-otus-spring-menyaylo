package ru.otus.spring.repository;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.domain.BookComment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий на основе Jpa для работы с книжными комментариями ")
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class BookCommentRepositoryTest {

    @Autowired
    private BookCommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("должен загружать список всех комментариев с полной информацией о них")
    @Test
    void shouldReturnCommentsWithAllInfo() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        List<BookComment> comments = repository.findAll();

        assertThat(comments).isNotNull().hasSize(5)
                .allMatch(bc -> !bc.getText().equals(""))
                .allMatch(bc -> bc.getBook() != null)
                .allMatch(bc -> bc.getBook().getGenre() != null)
                .allMatch(bc -> bc.getBook().getAuthor() != null);

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(1L);
    }
}