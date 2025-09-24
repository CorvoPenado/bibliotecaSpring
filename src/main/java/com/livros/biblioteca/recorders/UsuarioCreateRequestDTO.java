package com.livros.biblioteca.recorders;

public record UsuarioCreateRequestDTO(
        String nome,
        String email,
        Integer idade) {
}
