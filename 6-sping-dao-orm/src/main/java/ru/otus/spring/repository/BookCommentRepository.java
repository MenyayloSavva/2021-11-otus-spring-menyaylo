package ru.otus.spring.repository;

import ru.otus.spring.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentRepository {
    BookComment save(BookComment comment);

    Optional<BookComment> findById(long id);

    List<BookComment> findAll();

    void updateTextById(long id, String text);

    void deleteById(long id);
}
