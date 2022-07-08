package com.github.transformeli.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class InvalidClientParamException extends ApiException {

    public InvalidClientParamException(String message) {
        super(message);
        this.setStatus(HttpStatus.NOT_ACCEPTABLE);
        this.setTitle("Client param error");
    }
}
