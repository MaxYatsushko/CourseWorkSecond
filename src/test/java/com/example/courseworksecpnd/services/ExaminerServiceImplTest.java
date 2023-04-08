package com.example.courseworksecpnd.services;

import com.example.courseworksecpnd.entities.Question;
import com.example.courseworksecpnd.exceptions.QuestionAmountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ExaminerServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class ExaminerServiceImplTest {
    @Autowired
    private ExaminerService examinerService;

    @MockBean
    private QuestionService questionService;

    @Test
    void getQuestions_success() {
        //входные данные
        int amount = 1;
        Question q1 = new Question("Самая большая страна", "Россия");
        Question q2 = new Question("Самый используемый язык", "Английский");

        Set<Question> questions = Set.of(q1, q2);

        //ожидаемый результат
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(q1);
        Set<Question> expectedQuestions = Set.of(q1);

        //начало теста
        Collection<Question> actualQuestions = examinerService.getQuestions(amount);
        assertEquals(expectedQuestions, actualQuestions);
        verify(questionService).getAll();
        verify(questionService).getRandomQuestion();
        assertEquals(amount, actualQuestions.size());
    }

    @Test
    void getQuestions_QuestionAmountExceptionLess1() {
        //входные данные
        int amount = 0;

        //ожидаемый результат
        String expectedError = "Количество вопросов должно быть больше 0";


        //начало теста
        Exception exception = assertThrows(QuestionAmountException.class, () -> examinerService.getQuestions(amount));
        assertEquals(expectedError, exception.getMessage());
    }

    @Test
    void getQuestions_QuestionAmountExceptionDifferentSize() {
        //входные данные
        int amount = 10;

        //ожидаемый результат
        String expectedError = "В наличии меньше вопросов чем запрошено";


        //начало теста
        Exception exception = assertThrows(QuestionAmountException.class, () -> examinerService.getQuestions(amount));
        assertEquals(expectedError, exception.getMessage());
    }
}
