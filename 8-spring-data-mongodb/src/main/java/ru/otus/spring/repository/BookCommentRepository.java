package ru.otus.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.domain.BookComment;

import java.util.List;

public interface BookCommentRepository extends MongoRepository<BookComment, Long> {
    List<BookComment> findByBook_Id(long bookId);
}
