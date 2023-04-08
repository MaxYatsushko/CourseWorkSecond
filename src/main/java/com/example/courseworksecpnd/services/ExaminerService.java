package com.example.courseworksecpnd.services;

import com.example.courseworksecpnd.entities.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
