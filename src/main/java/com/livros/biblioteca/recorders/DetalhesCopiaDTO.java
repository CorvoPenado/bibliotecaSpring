package com.livros.biblioteca.recorders;

public record DetalhesCopiaDTO(String tituloLivro,
                               String nomeGenero,
                               String nomeAutor,
                               String statusCopia,
                               Integer quantidadeCopia)
{

}
