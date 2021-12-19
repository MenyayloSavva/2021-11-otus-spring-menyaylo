package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Жанр книги.
 */
@Data
@RequiredArgsConstructor
public final class Genre {
    /**
     * Идентификатор жанра.
     */
    private final int id;

    /**
     * Название жанра.
     */
    private final String name;
}
