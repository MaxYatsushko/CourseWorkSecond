package com.example.courseworksecpnd.services;

import com.example.courseworksecpnd.entities.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    Set<Question> questions = new HashSet<>();
    private final Random random;

    public JavaQuestionService(Random random) {
        this.random = random;
    }

    @Override
    public Question add(Question question) {
        questions.add(question);

        return question;
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);

        return newQuestion;
    }

    @Override
    public Question remove(String question, String answer) {
        Question deleteQuestion = new Question(question, answer);
        questions.remove(deleteQuestion);

        return deleteQuestion;
    }

    @Override
    public Question remove(Question question) {
        questions.remove(question);

        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(random.nextInt(questionList.size()));

    }
}
