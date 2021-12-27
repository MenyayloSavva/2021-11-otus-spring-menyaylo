package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

public interface BookDao {

    void insert(Book book);

    Book getById(int id);

    void update(Book book);

    void deleteById(int id);
}
