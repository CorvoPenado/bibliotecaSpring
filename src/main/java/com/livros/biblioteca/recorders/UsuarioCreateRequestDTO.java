package com.livros.biblioteca.recorders;

import jakarta.validation.constraints.*;

public record UsuarioCreateRequestDTO(

        @NotBlank(message = "O nome não pode estar em branco")
        @Size(min = 3,max = 50,message = "O nome deve contar de 3 a 50 caracteres")
        String nome,

        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "O formato do Email está incorreto")
        String email,

        @NotNull(message = "A idade não pode ser nula")
        @Min(value = 1,message = "A idade deve ser no mínimo 1")
        Integer idade
)
{ }
