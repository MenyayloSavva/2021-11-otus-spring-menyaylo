package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Книга.
 */
@Data
@RequiredArgsConstructor
public final class Book {
    /**
     * Идентификатор книги.
     */
    private final int id;

    /**
     * Название книги.
     */
    private final String name;

    /**
     * Год издания.
     */
    private final String yearOfPublication;

    /**
     * Автор.
     */
    private final Author author;

    /**
     * Жанр.
     */
    private final Genre genre;
}
