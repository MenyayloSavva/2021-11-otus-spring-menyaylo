package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void deleteById(long id);
}
