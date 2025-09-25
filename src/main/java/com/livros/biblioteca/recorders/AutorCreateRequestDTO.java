package com.livros.biblioteca.recorders;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorCreateRequestDTO(

        @NotBlank(message = "O nome do autor não pode estar vazio")
        @Size(min = 3, max = 50, message = "O nome do autor deve conter de 3 a 50 caracteres")
        String nome,
        @NotBlank(message = "O email não pode estar vazio")
        @Email(message = "O formato do email está incorreto!")
        String email
)
{
}
