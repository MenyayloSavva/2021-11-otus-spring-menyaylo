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
    @Transactional(readOnly = true)
    public Optional<Book> findById(int id) {
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
    public void updateNameById(int id, String name) {
        repository.updateNameById(id, name);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
