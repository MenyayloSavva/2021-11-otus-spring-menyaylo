package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.repository.BookCommentRepository;
import ru.otus.spring.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override
    public void save(BookComment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Optional<BookComment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<BookComment> findByBookId(long id) {
        return bookRepository.findAll().stream()
                .filter(b -> b.getId() == id)
                .map(Book::getComments)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookComment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public void updateTextById(long id, String text) {
        BookComment bookComment = commentRepository.findById(id).orElseThrow();
        bookComment.setText(text);
        commentRepository.save(bookComment);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
