package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;
import ru.otus.spring.repository.BookRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepositoryJpa repository;

    @Override
    @Transactional
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional
    public void updateNameById(long id, String name) {
        Optional<Book> book = repository.findById(id);
        book.ifPresent(b -> b.setName(name));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
