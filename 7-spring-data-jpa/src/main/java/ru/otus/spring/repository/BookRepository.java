package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "graph.Book")
    List<Book> findAll();

    List<Book> findByName(String name);

    @Modifying
    @Query("UPDATE Book b SET b.name = :name WHERE b.id = :id")
    void updateNameById(@Param("id") long id, @Param("name") String name);
}
