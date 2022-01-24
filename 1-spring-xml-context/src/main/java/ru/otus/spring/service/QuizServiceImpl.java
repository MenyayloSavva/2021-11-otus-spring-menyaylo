package ru.otus.spring.service;

import ru.otus.spring.domain.Item;

import java.util.List;
import java.util.Optional;

public class QuizServiceImpl implements QuizService {

    private final ItemService itemService;

    public QuizServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void interact() {
        System.out.println("\n\nList all questions:");
        List<Item> items = itemService.findAll();
        items.forEach(System.out::println);

        System.out.println("\n\nSearch by question name:");
        Optional<Item> item = itemService.findByQuestion("What year was the first Toy Story film released in cinemas?");
        item.ifPresent(System.out::println);
    }
}
