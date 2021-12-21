package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.domain.Book;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDaoJdbc bookDao;
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;

    @Override
    public void insertBook(Book book) {
        bookDao.insert(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.update(book);
    }

    @Override
    public Book getBook(int id) {
        Book book = bookDao.getById(id);
        return book.toBuilder()
                .author(authorService.getAuthor(book.getAuthor().getId()))
                .genre(genreService.getGenre(book.getGenre().getId()))
                .build();
    }

    @Override
    public void deleteBook(int id) {
        bookDao.deleteById(id);
    }

}
