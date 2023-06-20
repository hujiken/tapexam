package com.thv.tapexam.controller;

import com.thv.tapexam.dto.request.SubjectRequest;
import com.thv.tapexam.entity.Subject;
import com.thv.tapexam.exception.ResponseException;
import com.thv.tapexam.service.base.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/{subjectId}")
    public ResponseEntity<Object> getSubject(@PathVariable Integer subjectId) {
        try {
            Optional<Subject> subjectOptional = subjectService.findById(subjectId);
            return new ResponseEntity<>(subjectOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllSubject() {
        try {
            Optional<List<Subject>> subjectOptional = subjectService.getAllSubject();
            return new ResponseEntity<>(subjectOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addSubject(@Validated @RequestBody SubjectRequest subjectRequest) {
        try {
            return new ResponseEntity<>(subjectService.addSubject(subjectRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<Object> updateSubject(@PathVariable Integer subjectId, @Validated @RequestBody SubjectRequest subjectRequest) {
        try {
            return new ResponseEntity<>(subjectService.updateSubject(subjectId, subjectRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Object> deleteSubject(@PathVariable Integer subjectId) {
        try {
            return new ResponseEntity<>(subjectService.deleteSubject(subjectId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }
}
