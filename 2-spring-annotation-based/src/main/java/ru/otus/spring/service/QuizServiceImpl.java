package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Item;

import java.util.List;
import java.util.Scanner;

@Service
public class QuizServiceImpl implements QuizService {

    private final ItemService itemService;

    @Value("${answers.required_amount}")
    private int requiredAmount;

    public QuizServiceImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public void interact() {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = itemService.findAll();

        System.out.println("Type your name and surname, please:");
        String fullName = scanner.nextLine();

        int score = 0;
        System.out.println("\nBe ready to get questioned!");
        for (Item item : items) {
            System.out.println(item.getQuestion());
            String answer = scanner.nextLine();

            if (item.getAnswer().equalsIgnoreCase(answer)) {
                score++;
            }
        }

        System.out.println("\nDear, mr(s) " + fullName + ", your total score is " + score + ".");
        System.out.println(score >= requiredAmount ? "Quiz completed!" : "Quiz failed!");
    }
}
