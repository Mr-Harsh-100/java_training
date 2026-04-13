package com.example.assignment.exception;


import com.example.assignment.dto.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        String message = "Duplicate value violates unique constraint";

        Throwable root = ex.getRootCause();

        if (root != null && root.getMessage() != null) {

            String dbMessage = root.getMessage();

            // PostgreSQL format: Key (email)=(value) already exists
            if (dbMessage.contains("Key (")) {
                try {
                    int start = dbMessage.indexOf("Key (") + 5;
                    int end = dbMessage.indexOf(")=", start);

                    String field = dbMessage.substring(start, end);

                    message = "Duplicate value for field: " + field;

                } catch (Exception ignored) {}
            }
        }

        return new ErrorResponse(400, message);
    }


}
