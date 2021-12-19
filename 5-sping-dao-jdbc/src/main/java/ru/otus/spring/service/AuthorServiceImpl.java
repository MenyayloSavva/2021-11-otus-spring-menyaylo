package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.domain.Author;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDaoJdbc authorDao;

    @Override
    public Author getAuthor(int id) {
        return authorDao.getById(id);
    }
}
