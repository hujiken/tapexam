package com.thv.tapexam.controller;

import com.thv.tapexam.dto.request.DivisionRequest;
import com.thv.tapexam.entity.Division;
import com.thv.tapexam.exception.ResponseException;
import com.thv.tapexam.service.base.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/division")
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @GetMapping("/{divisionId}")
    public ResponseEntity<Object> getDivision(@PathVariable Integer divisionId) {
        try {
            Optional<Division> divisionOptional = divisionService.findById(divisionId);
            return new ResponseEntity<>(divisionOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllDivision() {
        try {
            Optional<List<Division>> divisionOptional = divisionService.getAllDivision();
            return new ResponseEntity<>(divisionOptional.get(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addDivision(@Validated @RequestBody DivisionRequest divisionRequest) {
        try {
            return new ResponseEntity<>(divisionService.addDivision(divisionRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @PutMapping("/{divisionId}")
    public ResponseEntity<Object> updateDivision(@PathVariable Integer divisionId, @Validated @RequestBody DivisionRequest divisionRequest) {
        try {
            return new ResponseEntity<>(divisionService.updateDivision(divisionId, divisionRequest), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }

    @DeleteMapping("/{divisionId}")
    public ResponseEntity<Object> deleteDivision(@PathVariable Integer divisionId) {
        try {
            return new ResponseEntity<>(divisionService.deleteDivision(divisionId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseException.response(e);
        }
    }
}
