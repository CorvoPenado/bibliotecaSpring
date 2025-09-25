package com.livros.biblioteca.controllers;

import com.livros.biblioteca.models.Genero;
import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.GeneroCreateRequestDTO;
import com.livros.biblioteca.recorders.UsuarioCreateRequestDTO;
import com.livros.biblioteca.services.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    private final GeneroService generoService;

    @Autowired
    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Genero> generoCreate(@Validated @RequestBody GeneroCreateRequestDTO genero){

        Genero createGenero = generoService.generoCreate(genero);

        return new ResponseEntity<>(createGenero, HttpStatus.CREATED);
    }

}
