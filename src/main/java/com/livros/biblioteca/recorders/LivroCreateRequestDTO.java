package com.livros.biblioteca.recorders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record LivroCreateRequestDTO(

        @NotBlank(message = "O titulo do livro não pode estar vazio")
        @Size(min = 3,max = 50,message = "TÍTULO DEVE CONTAR DE 3 A 50 CARACTERES")
        String titulo,
        @NotBlank(message = "A sinopse do livro não pode estar vazio")
        @Size(min = 3,max = 355,message = "SINOPSE DEVE CONTAR DE 3 A 355 CARACTERES")
        String sinopse,
        @NotNull(message = "AUTOR ID NÃO PODE SER NULO")
        Long autorId,
        @NotNull(message = "GENERO ID NÃO PODE SER NULO")
        Long generoId) {
}
