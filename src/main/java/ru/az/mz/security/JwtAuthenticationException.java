package ru.az.mz.security;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.naming.AuthenticationException;

public final class JwtAuthenticationException extends AuthenticationException {

    @Getter
    private HttpStatus httpStatus;

    public JwtAuthenticationException(String message) {
        super(message);
    }

    public JwtAuthenticationException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
