package ru.otus.spring.service;

import ru.otus.spring.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Optional<Item> findByQuestion(String question);

    List<Item> findAll();
}
