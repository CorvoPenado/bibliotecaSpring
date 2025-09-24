package com.livros.biblioteca.models;

import jakarta.persistence.*;

@Entity
@Table(name = "copias")
public class Copia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", columnDefinition = "VARCHAR(24) DEFAULT 'DISPONÍVEL'")
    private String status = "DISPONÍVEL";

    @Column(name = "quantidade", columnDefinition = "INTEGER DEFAULT 1")
    private Integer quantidade = 1;

    @ManyToOne
    @JoinColumn(name = "livro_id",nullable = false)
    private Livro livro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer qtd) {
        this.quantidade = qtd;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
