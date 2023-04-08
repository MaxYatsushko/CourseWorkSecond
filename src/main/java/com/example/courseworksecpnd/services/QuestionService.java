package com.example.courseworksecpnd.services;

import com.example.courseworksecpnd.entities.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(Question question);
    Question add(String question, String answer);
    Question remove(Question question);
    Question remove(String question, String answer);
    Collection<Question> getAll();
    Question getRandomQuestion();

}
