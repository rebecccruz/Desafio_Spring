package com.github.transformeli.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    public BadRequestException(String message) {
        super(message);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }

}
