package ru.az.mz.services;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class MyException extends Exception {

    @Getter
    private final HttpStatus httpStatus;

    public MyException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public MyException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
