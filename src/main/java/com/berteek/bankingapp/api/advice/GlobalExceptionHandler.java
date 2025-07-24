package com.berteek.bankingapp.api.advice;

import com.berteek.bankingapp.api.controller.WalletController;
import com.berteek.bankingapp.service.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage());
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
        logger.error(e.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(new Result(Result.Status.FAILURE, "Unexpected behavior", null));
    }
}
