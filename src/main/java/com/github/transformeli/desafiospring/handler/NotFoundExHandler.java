package com.github.transformeli.desafiospring.handler;

import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.exception.NotFoundExceptionDetalhes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class NotFoundExHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetalhes> handlerNotFoundEx(NotFoundException ex){
        return new ResponseEntity<>(
                NotFoundExceptionDetalhes.builder()
                        .title("Objeto nao encontrado")
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .timestamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }
}
