package com.github.transformeli.desafiospring.exception;

import org.springframework.http.HttpStatus;

public class PreConditionFailedException extends ApiException {

    public PreConditionFailedException(String message) {
        super(message);
        this.setStatus(HttpStatus.PRECONDITION_FAILED);
    }

}
