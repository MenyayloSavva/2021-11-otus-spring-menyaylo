package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.repository.BookCommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepository repository;

    @Override
    public void save(BookComment comment) {
        repository.save(comment);
    }

    @Override
    public Optional<BookComment> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<BookComment> findByBookId(long id) {
        return repository.findByBook_Id(id);
    }

    @Override
    public List<BookComment> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void updateTextById(long id, String text) {
        Optional<BookComment> bookComment = repository.findById(id);
        bookComment.ifPresent(bc -> bc.setText(text));
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
