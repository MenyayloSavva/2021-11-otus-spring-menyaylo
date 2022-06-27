package ru.otus.spring.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.service.QuizService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("ApplicationCommandsTest должен: ")
class ApplicationCommandsTest {

    @Autowired
    private ApplicationCommands applicationCommands;

    @MockBean
    private QuizService quizService;

    @Test
    @DisplayName("запускать приложение через Spring Shell ")
    void startQuizApplication() {
        applicationCommands.startQuizApplication();
        verify(quizService, times(1)).interact();
    }
}