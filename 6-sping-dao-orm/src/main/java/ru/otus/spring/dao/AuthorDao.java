package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

public interface AuthorDao {

    Author getById(int id);
}
