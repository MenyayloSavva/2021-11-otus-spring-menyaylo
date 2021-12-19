package ru.otus.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * Автор книги.
 */
@Data
@RequiredArgsConstructor
public final class Author {
    /**
     * Идентификатор автора.
     */
    private final int id;

    /**
     * Имя автора
     */
    private final String name;

    /**
     * Страна.
     */
    private final String country;

    /**
     * Дата рождения.
     */
    private final LocalDate birthDate;
}
