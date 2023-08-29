package com.practice.dummymicroservice.exception;

public class DuplicateException extends RuntimeException{
    String errorMessage;
    public DuplicateException(String s) {
        this.errorMessage = s;
    }
}
