package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

public interface BookService {

    void insertBook(Book book);

    void updateBook(Book book);

    Book getBook(int id);

    void deleteBook(int id);
}
