package com.khanhnhb.springweb.exception;

public class StudentAlreadyExistedException extends RuntimeException {
    public StudentAlreadyExistedException(String message) {
        super(message);
    }
}
