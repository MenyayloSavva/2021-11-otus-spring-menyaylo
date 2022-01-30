package ru.otus.spring.dao;

import ru.otus.spring.domain.Item;

import java.util.List;

public interface ItemDao {
    List<Item> findAll();
}
