package ru.az.mz.services;

import org.springframework.http.HttpStatus;

public class NotFoundException extends MyException{

    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public NotFoundException(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
