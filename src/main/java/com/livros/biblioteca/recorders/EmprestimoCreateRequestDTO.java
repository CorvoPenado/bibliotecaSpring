package com.livros.biblioteca.recorders;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EmprestimoCreateRequestDTO(

        @NotBlank(message = "Data de Termino não pode ser vazia")
        LocalDateTime dataTermino,
        @NotNull(message = "Quantidade não pode ser nula")
        @Min(value = 1, message = "Quantidade deve ser no mínimo 1")
        Integer quantidade,
        @NotNull(message = "USUARIO ID NÃO PODE SER NULO")
        Long usuarioId,
        @NotNull(message = "COPIA ID NÃO PODE SER NULO")
        Long copiaId) {
}
