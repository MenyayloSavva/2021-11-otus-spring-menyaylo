package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {
    @EntityGraph(value = "graph.BookComment")
    List<BookComment> findAll();

    List<BookComment> findByBook_Id(long bookId);
}
