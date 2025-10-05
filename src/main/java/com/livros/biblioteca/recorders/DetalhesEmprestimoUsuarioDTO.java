package com.livros.biblioteca.recorders;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record DetalhesEmprestimoUsuarioDTO(
        Long id,
        String nomeUsuario,
        String tituloLivro,
        Integer quantidadeCopias,
        Boolean emDiaEmprestimo,
        Boolean finalizadoEmprestimo,
        Integer multaEmprestimo,
        LocalDateTime dataTerminoEmprestimo,
        LocalDateTime dataFinalizadoEmprestimo
) {}
