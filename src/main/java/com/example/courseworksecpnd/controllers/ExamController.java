package com.example.courseworksecpnd.controllers;

import com.example.courseworksecpnd.entities.Question;
import com.example.courseworksecpnd.exceptions.QuestionAmountException;
import com.example.courseworksecpnd.services.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/get")
public class ExamController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({QuestionAmountException.class})
    public String handleException(RuntimeException e){
        return String.format("%s %s", HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public Collection<Question> getAll(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
