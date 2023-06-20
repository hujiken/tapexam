package com.thv.tapexam.controller;

import com.thv.tapexam.entity.Subject;
import com.thv.tapexam.exception.ResponseException;
import com.thv.tapexam.service.base.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<Object> getResultExam() {
        try {
            return new ResponseEntity<>(examService.getResultExam(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }
}
