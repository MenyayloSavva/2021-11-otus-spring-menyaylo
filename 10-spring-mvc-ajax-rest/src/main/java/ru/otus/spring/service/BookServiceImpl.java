package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public Optional<Book> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Book> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void updateNameById(long id, String name) {
        Book book = repository.findById(id).orElseThrow();
        book.setName(name);
        repository.save(book);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Book createBlankBook() {
        return Book.builder()
                .id(findAll().stream()
                        .max(Comparator.comparing(Book::getId))
                        .map(Book::getId)
                        .orElse(0L) + 1)
                .author(Author.builder().id(1).build())
                .genre(Genre.builder().id(1).build())
                .build();
    }
}
