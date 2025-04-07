package com.example.demoCrudRestFulApi.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDuplicateKeyException(DataIntegrityViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CONFLICT.value());

        String rootMsg = ex.getMostSpecificCause().getMessage();
        List<String> errors = new ArrayList<>();

        if (rootMsg.contains("email")) {
            errors.add("Email đã tồn tại trong hệ thống");
        } else if (rootMsg.contains("ma") || rootMsg.contains("ma_hoc_sinh") || rootMsg.contains("UQ__hoc_sinh")) {
            errors.add("Mã học sinh đã tồn tại trong hệ thống");
        } else {
            errors.add("Dữ liệu đã tồn tại trong hệ thống (lỗi ràng buộc duy nhất)");
        }

        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }


}
