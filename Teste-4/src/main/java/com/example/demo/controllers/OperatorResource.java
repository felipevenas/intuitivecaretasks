package com.example.demo.controllers;

import com.example.demo.domain.entities.Operator;
import com.example.demo.services.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/operators")
public class OperatorResource {

    @Autowired
    private OperatorService operatorService;

    @GetMapping("/{registro_ans}")
    public ResponseEntity<Optional<Operator>> searchOperators(@PathVariable("registro_ans") String registro_ans) {
        var operator = operatorService.findOperatorById(registro_ans);
        if (registro_ans != null) {
            return ResponseEntity.ok(operator);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Operator>> listOperators() {
        var operators = operatorService.findAll();
        return ResponseEntity.ok(operators);
    }

}
