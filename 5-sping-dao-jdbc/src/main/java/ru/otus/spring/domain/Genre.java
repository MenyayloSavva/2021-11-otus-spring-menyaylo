package ru.otus.spring.domain;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Жанр книги.
 */
@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
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
