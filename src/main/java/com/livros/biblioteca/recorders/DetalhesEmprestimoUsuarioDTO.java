package com.livros.biblioteca.recorders;

import java.time.LocalDateTime;

public record DetalhesEmprestimoUsuarioDTO(String nomeUsuario, String tituloLivro, Integer quantidadeCopias, Boolean emDiaEmprestimo, Boolean FinalizadoEmprestimo, Integer MultaEmprestimo,
                                           LocalDateTime dataTerminoEmprestimo) {
}
