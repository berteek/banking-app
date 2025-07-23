package com.berteek.bankingapp.api.advice;

import com.berteek.bankingapp.service.model.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }

        return ResponseEntity
                .badRequest()
                .body(new Result(Result.Status.FAILURE, errors.toString(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> exception(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(new Result(Result.Status.FAILURE, "Unexpected behavior", null));
    }
}
