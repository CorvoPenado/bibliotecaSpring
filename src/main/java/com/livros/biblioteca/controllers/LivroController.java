package com.livros.biblioteca.controllers;

import com.livros.biblioteca.models.Livro;
import com.livros.biblioteca.recorders.DetalhesLivrosAutorDTO;
import com.livros.biblioteca.recorders.DetalhesLivrosGeneroDTO;
import com.livros.biblioteca.recorders.LivroCreateRequestDTO;
import com.livros.biblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping("/create")
    public ResponseEntity<Livro> livroCreate(@Validated @RequestBody LivroCreateRequestDTO livro){
        Livro createLivro = livroService.livroCreate(livro);

        return new ResponseEntity<>(createLivro, HttpStatus.CREATED);
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> livroDeletar(@PathVariable Long id){
        livroService.deletarLivro(id);

        return ResponseEntity.noContent().build();
    }*/

    @GetMapping("/lista")
    public List<Livro> getAllLivros(){
        return livroService.getLivros();
    }

    @GetMapping("/autor/livros/{nomeAutor}")
    public List<DetalhesLivrosAutorDTO> getLivrosAutor(@PathVariable String nomeAutor){
        return livroService.encontrarLivrosAutor(nomeAutor);
    }

    @GetMapping("/genero/livros/{nomeGenero}")
    public List<DetalhesLivrosGeneroDTO> getLivrosGenero(@PathVariable String nomeGenero){
        return livroService.encontrarLivrosGenero(nomeGenero);
    }

}
