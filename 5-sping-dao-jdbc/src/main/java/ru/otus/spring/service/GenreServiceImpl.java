package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDaoJdbc;
import ru.otus.spring.domain.Genre;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDaoJdbc genreDao;

    public Genre getGenre(int id) {
        return genreDao.getById(id);
    }
}
