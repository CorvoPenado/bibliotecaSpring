package com.livros.biblioteca.recorders;

import java.time.LocalDateTime;

public record LivroCreateRequestDTO(String titulo, String sinopse, Long autorId,Long generoId) {
}
