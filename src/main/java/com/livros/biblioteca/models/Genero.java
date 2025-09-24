package com.livros.biblioteca.models;

import jakarta.persistence.*;


@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome",length = 32,nullable = false)
    private String nome;

}
