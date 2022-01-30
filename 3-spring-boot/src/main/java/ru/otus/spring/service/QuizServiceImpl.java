package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.configuration.ProjectConfigs;
import ru.otus.spring.domain.Item;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final ItemService itemService;
    private final MessageSource messageSource;
    private final Locale locale;
    private final ProjectConfigs projectConfigs;

    @Override
    public void interact() {
        Scanner scanner = new Scanner(System.in);
        List<Item> items = itemService.findAll();

        System.out.println(messageSource.getMessage("strings.intro.message", null, locale));
        String fullName = scanner.nextLine();

        int score = 0;
        System.out.println(messageSource.getMessage("strings.intro.readiness", null, locale));
        for (Item item : items) {
            System.out.println(item.getQuestion());
            String answer = scanner.nextLine();

            if (item.getAnswer().equalsIgnoreCase(answer)) {
                score++;
            }
        }

        System.out.println(messageSource.getMessage("strings.outro.score", new String[]{fullName, String.valueOf(score)}, locale));
        System.out.println(score >= projectConfigs.getAnswer().getRequiredAmount() ?
                messageSource.getMessage("strings.outro.passed", null, locale) :
                messageSource.getMessage("strings.outro.failed", null, locale));
    }
}
