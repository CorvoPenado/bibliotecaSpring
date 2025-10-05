package com.livros.biblioteca.recorders;

public record DetalhesCopiaDTO(
                               Long id,
                               String tituloLivro,
                               String nomeGenero,
                               String nomeAutor,
                               String statusCopia,
                               Integer quantidadeCopia)
{

}
