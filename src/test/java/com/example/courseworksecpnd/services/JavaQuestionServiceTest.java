package com.example.courseworksecpnd.services;

import com.example.courseworksecpnd.entities.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {JavaQuestionService.class})
@ExtendWith(SpringExtension.class)
class JavaQuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @MockBean
    private Random random;

    @BeforeEach
    void initEmployees(){
        questionService.init();
    }

    @Test
    void getRandomQuestion_success() {
        //входные данные
        Question q1 = new Question("Самая большая страна", "Россия");
        Question q2 = new Question("Самый используемый язык", "Английский");

        //ожидаемый результат
        when(random.nextInt(2)).thenReturn(1);

       //начало теста
        questionService.add(q1);
        questionService.add(q2);

        Question actualQuestion = questionService.getRandomQuestion();
        assertEquals(q2, actualQuestion);
    }

    @Test
    void addQuestion_success() {
        //ожидаемый результат
        Question expectedQuestion = new Question("Самая большая страна", "Россия");
        Set<Question> expectedQuestions = Set.of(expectedQuestion);

        //начало теста
        Question actualQuestion = questionService.add(expectedQuestion);
        assertEquals(expectedQuestion, actualQuestion);
        assertTrue(expectedQuestions.size() == questionService.getAll().size());
    }

    @Test
    void addString_success() {
        //входные данные
        String question = "Самая большая страна", answer = "Россия";

        //ожидаемый результат
        Question expectedQuestion = new Question(question, answer);
        Set<Question> expectedQuestions = Set.of(expectedQuestion);

        //начало теста
        Question actualQuestion = questionService.add(question, answer);
        assertEquals(expectedQuestion, actualQuestion);
        assertTrue(expectedQuestions.size() == questionService.getAll().size());
    }


    @Test
    void removeQuestion_success() {
        //ожидаемый результат
        Question expectedQuestion = new Question("Самая большая страна", "Россия");
        Set<Question> expectedQuestions = new HashSet<>();


        //начало теста
        Question actualQuestion = questionService.remove(expectedQuestion);
        assertEquals(expectedQuestion, actualQuestion);
        assertTrue(expectedQuestions.size() == questionService.getAll().size());
    }

    @Test
    void removeString_success() {
        //входные данные
        String question = "Самая большая страна", answer = "Россия";

        //ожидаемый результат
        Question expectedQuestion = new Question(question, answer);
        Set<Question> expectedQuestions = new HashSet<>();

        //начало теста
        Question actualQuestion = questionService.remove(question, answer);
        assertEquals(expectedQuestion, actualQuestion);
        assertTrue(expectedQuestions.size() == questionService.getAll().size());
    }

    @Test
    void getAll_success(){
        //входные данные
        Question q1 = new Question("Самая большая страна", "Россия");
        Question q2 = new Question("Самый используемый язык", "Английский");

        //ожидаемый результат
        Set<Question> expectedQuestions = Set.of(q1, q2);

        //начало теста
        questionService.add(q1);
        questionService.add(q2);
        Collection<Question> actualQuestions = questionService.getAll();

        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void getAll_empty(){
        //входные данные
        Set<Question> expectedQuestions = new HashSet<>();

        //ожидаемый результат
        Collection<Question> actualQuestions = questionService.getAll();

        //начало теста
        assertEquals(expectedQuestions, actualQuestions);
    }
}
