package com.livros.biblioteca.recorders;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CopiaCreateRequestDTO(
        String status,
        Long livroId
)
{
}
