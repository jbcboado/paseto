package com.practice.dummymicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> productNotFoundException(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @ExceptionHandler(value = ProductIncompleteException.class)
    public ResponseEntity<?> productIncompleteDetailsException(ProductIncompleteException e){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Incomplete details...");
    }
}
