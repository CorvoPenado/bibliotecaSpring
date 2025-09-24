package com.livros.biblioteca.controllers;


import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.UsuarioCreateRequestDTO;
import com.livros.biblioteca.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> usuarioCreate(@RequestBody UsuarioCreateRequestDTO user){

        Usuario createUsuario = usuarioService.usuarioCreate(user);

        return new ResponseEntity<>(createUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/lista")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getUsuarios();
    }

}
