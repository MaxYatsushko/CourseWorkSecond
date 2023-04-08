package com.example.courseworksecpnd.services;

import com.example.courseworksecpnd.entities.Question;
import com.example.courseworksecpnd.exceptions.QuestionAmountException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {

        if(amount < 1)
            throw new QuestionAmountException("Количество вопросов должно быть больше 0");

        if (amount > questionService.getAll().size())
            throw new QuestionAmountException("В наличии меньше вопросов чем запрошено");

        Set<Question> result = new HashSet<>();

        while (result.size() < amount)
            result.add(questionService.getRandomQuestion());

        return result;
    }
}
