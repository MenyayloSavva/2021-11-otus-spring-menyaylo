package ru.otus.spring.repository;

import ru.otus.spring.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentRepository {
    BookComment save(BookComment comment);

    Optional<BookComment> findById(int id);

    List<BookComment> findAll();

    void updateTextById(int id, String text);

    void deleteById(int id);
}
