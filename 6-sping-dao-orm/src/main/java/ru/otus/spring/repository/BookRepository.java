package ru.otus.spring.repository;

import ru.otus.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);

    Optional<Book> findById(int id);

    List<Book> findAll();

    List<Book> findByName(String name);

    void updateNameById(int id, String name);

    void deleteById(int id);
}
