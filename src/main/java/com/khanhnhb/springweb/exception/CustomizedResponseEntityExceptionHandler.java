package com.khanhnhb.springweb.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .count(1)
                .error(ex.getMessage())
                .errors(Arrays.asList(ex.getMessage()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public final ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .count(1)
                .error(ex.getMessage())
                .errors(Arrays.asList(ex.getMessage()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistedException.class)
    public final ResponseEntity<Object> handleStudentAlreadyExistedException(StudentAlreadyExistedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .count(1)
                .error(ex.getMessage())
                .errors(Arrays.asList(ex.getMessage()))
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .count(bindingResult.getErrorCount())
                .error("Validation Failed")
                .errors(bindingResult.getAllErrors()
                        .stream()
                        .map(error -> error.getCode())
                        .collect(Collectors.toList()))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}