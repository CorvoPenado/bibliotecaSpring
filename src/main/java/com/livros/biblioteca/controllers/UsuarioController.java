package com.livros.biblioteca.controllers;


import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.PerfilUsuarioDTO;
import com.livros.biblioteca.recorders.UsuarioCreateRequestDTO;
import com.livros.biblioteca.services.UsuarioService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> usuarioCreate(@Validated @RequestBody UsuarioCreateRequestDTO user){

        Usuario createUsuario = usuarioService.usuarioCreate(user);

        return new ResponseEntity<>(createUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public ResponseEntity<PerfilUsuarioDTO> getPerfilUsuario(@AuthenticationPrincipal Usuario usuario){
        PerfilUsuarioDTO perfilDTO = new PerfilUsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getIdade()
        );

        return ResponseEntity.ok(perfilDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> usuarioDeletar(@PathVariable Long id){
        usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lista")
    public List<Usuario> getAllUsuarios(){
        return usuarioService.getUsuarios();
    }

}
