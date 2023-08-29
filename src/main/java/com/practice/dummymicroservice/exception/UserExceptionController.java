package com.practice.dummymicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = DuplicateException.class)
    ResponseEntity<?> duplicateUserException(DuplicateException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.errorMessage);
    }

}
