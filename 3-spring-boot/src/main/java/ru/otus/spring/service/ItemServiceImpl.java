package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ItemDao;
import ru.otus.spring.domain.Item;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    @Override
    public Optional<Item> findByQuestion(String question) {
        return itemDao.findAll().stream()
                .filter(i -> i.getQuestion().equals(question))
                .findFirst();
    }

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }
}
