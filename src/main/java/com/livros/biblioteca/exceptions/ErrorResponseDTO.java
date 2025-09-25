package com.livros.biblioteca.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, HttpStatus status, int statusCode, LocalDateTime timestamp) {
}
