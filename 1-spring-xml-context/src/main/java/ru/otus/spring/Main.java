package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Item;
import ru.otus.spring.service.ItemService;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        ItemService service = context.getBean(ItemService.class);

        System.out.println("\n\nList all questions:");
        List<Item> items = service.findAll();
        items.forEach(System.out::println);

        System.out.println("\n\nSearch by question name:");
        Optional<Item> item = service.findByQuestion("What year was the first Toy Story film released in cinemas?");
        item.ifPresent(System.out::println);
    }
}
