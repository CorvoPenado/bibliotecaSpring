package com.livros.biblioteca.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Basicamente foi montado aqui "Catchs" de exceptions, as exceptions ja feitas não precisam
    //ser alteradas! usando isso, ja está dizendo ao SPRING: "Ei, Spring, fiz uma camada nova
    //de tratamento de erro, quando for lançar uma exception, verifique o tipo dela e
    //Chame o método correspondentes que já está PRONTO aqui.

    //Repare que não preciso usar um DTO, mas fica mais organizado assim.
    //Consigo retornar mensagem, erro, data e hora do mesmo, muito melhor
    //Mas se quiser praticidade basta apenas não retornar caceta nenhuma
    //Apenas a STRING que você declarou em outros exceptions, só retornar isso e pronto
    //Não precisa de um DTO, aí poderia retornar só um return ex. fim de papo, ou converte pra STRING
    //E depois retornar a STRING, mas pelo que lembro não era assim, mas caso tente algo
    //Ta aí duas possibilidades kkkkk

    //Funciona pra qualquer LUGAR que não tenha um Handler muito específico
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        //retorna o http 404
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //Variação de Exception
    //handler pra outro tipo de exception
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        //Retorna erro 400 pra argumentos inválidos
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalStateException(IllegalStateException ex) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        //Retorna erro 404
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponseDTO> handleSecurityException(SecurityException ex) {

        ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        //Retorna erro 404
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
