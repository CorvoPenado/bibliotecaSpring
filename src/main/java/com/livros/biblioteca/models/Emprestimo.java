package com.livros.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "data_emprestimo",updatable = false)
    private LocalDateTime dataEmprestimo;

    @Column(name = "data_emprestimo_termino", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEmprestimoTermino;

    @Column(name = "data_finalizado_emprestimo")
    private LocalDateTime dataFinalizadoEmprestimo;

    @Column(name = "quantidade", columnDefinition = "INTEGER DEFAULT 1")
    private Integer quantidade = 1;

    @Column(name = "em_dia",columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean emDia =  true;

    @Column(name = "finalizado", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean finalizado = false;

    @Column(name = "multa",columnDefinition = "INTEGER DEFAULT 0 ")
    private Integer multa = 0;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "copia_id",nullable = false)
    private Copia copia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDateTime getDataEmprestimoTermino() {
        return dataEmprestimoTermino;
    }

    public void setDataEmprestimoTermino(LocalDateTime dataEmprestimoTermino) {
        this.dataEmprestimoTermino = dataEmprestimoTermino;
    }
    public LocalDateTime getDataFinalizadoEmprestimo() {
        return dataFinalizadoEmprestimo;
    }

    public void setDataFinalizadoEmprestimo(LocalDateTime dataFinalizadoEmprestimo) {
        this.dataFinalizadoEmprestimo = dataFinalizadoEmprestimo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getEmDia() {
        return emDia;
    }

    public void setEmDia(Boolean emDia) {
        this.emDia = emDia;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Integer getMulta() {
        return multa;
    }

    public void setMulta(Integer multa) {
        this.multa = multa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Copia getCopia() {
        return copia;
    }

    public void setCopia(Copia copia) {
        this.copia = copia;
    }
}
