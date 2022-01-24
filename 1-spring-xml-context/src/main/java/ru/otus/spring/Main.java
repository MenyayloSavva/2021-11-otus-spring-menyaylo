package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Item;
import ru.otus.spring.service.ItemService;
import ru.otus.spring.service.QuizService;
import ru.otus.spring.service.QuizServiceImpl;

import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService quizService = context.getBean(QuizServiceImpl.class);
        quizService.interact();
    }
}
