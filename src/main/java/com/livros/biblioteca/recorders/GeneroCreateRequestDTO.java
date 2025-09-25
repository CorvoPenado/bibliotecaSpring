package com.livros.biblioteca.recorders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GeneroCreateRequestDTO(
        @NotBlank(message = "Genero não pode ser vazio")
        @Size(min = 1, max = 25,message = "Genero deve conter de 1 a 25 caracteres")
        String nome
)
{
}
