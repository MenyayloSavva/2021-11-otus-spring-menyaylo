package ru.otus.spring.dto;

import lombok.Data;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;

import java.util.List;

@Data
public final class BookRequest {
    private long id;

    private String name;

    private String yearOfPublication;

    private Author author;

    private Genre genre;

    private List<BookComment> comments;
}
