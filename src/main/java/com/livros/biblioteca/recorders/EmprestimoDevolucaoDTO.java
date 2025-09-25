package com.livros.biblioteca.recorders;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmprestimoDevolucaoDTO(
        @NotNull(message = "ID DO USUARIO NÃO PODE SER NULO")
        Long usuarioId,
        @NotNull(message = "QUANTIDADE NÃO PODE SER NULA")
        @Min(value = 1,message = "QUANTIDADE MÍNIMA DEVE SER 1")
        Integer quantidadeDevolvida
) {
}
