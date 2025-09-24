package com.livros.biblioteca.controllers;

import com.livros.biblioteca.models.Autor;
import com.livros.biblioteca.recorders.AutorCreateRequestDTO;
import com.livros.biblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Autor> autorCreate(@RequestBody AutorCreateRequestDTO autor){

        Autor createAutor = autorService.autorCreate(autor);

        return new ResponseEntity<>(createAutor, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public List<Autor> getAllAutores(){
        return autorService.getAutores();
    }


}
