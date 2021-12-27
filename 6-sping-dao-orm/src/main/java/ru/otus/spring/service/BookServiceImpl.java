package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.domain.Book;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDaoJdbc bookDao;

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
        return bookDao.getById(id);
    }

    @Override
    public void deleteBook(int id) {
        bookDao.deleteById(id);
    }

}
