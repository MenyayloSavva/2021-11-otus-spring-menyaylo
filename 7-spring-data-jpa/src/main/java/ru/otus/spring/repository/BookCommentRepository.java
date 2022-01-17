package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

    @EntityGraph(value = "graph.BookComment")
    List<BookComment> findAll();

    List<BookComment> findByBook_Id(long bookId);

    @Modifying
    @Query("UPDATE BookComment bc SET bc.text = :text WHERE bc.id = :id")
    void updateTextById(@Param("id") long id, @Param("text") String text);
}
