package com.thv.tapexam.controller;

import com.thv.tapexam.dto.request.ConditionRequest;
import com.thv.tapexam.entity.Condition;
import com.thv.tapexam.exception.ResponseException;
import com.thv.tapexam.service.base.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/condition")
public class ConditionController {

    @Autowired
    private ConditionService conditionService;

    @GetMapping("/{conditionId}")
    public ResponseEntity<Object> getCondition(@PathVariable Integer conditionId) {
        try {
            Optional<Condition> conditionOptional = conditionService.findById(conditionId);
            return new ResponseEntity<>(conditionOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllCondition() {
        try {
            Optional<List<Condition>> conditionOptional = conditionService.getAllCondition();
            return new ResponseEntity<>(conditionOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCondition(@Validated @RequestBody ConditionRequest conditionRequest) {
        try {
            return new ResponseEntity<>(conditionService.addCondition(conditionRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @PutMapping("/{conditionId}")
    public ResponseEntity<Object> updateCondition(@PathVariable Integer conditionId, @Validated @RequestBody ConditionRequest conditionRequest) {
        try {
            return new ResponseEntity<>(conditionService.updateCondition(conditionId, conditionRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @DeleteMapping("/{conditionId}")
    public ResponseEntity<Object> deleteCondition(@PathVariable Integer conditionId) {
        try {
            return new ResponseEntity<>(conditionService.deleteCondition(conditionId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }
}
