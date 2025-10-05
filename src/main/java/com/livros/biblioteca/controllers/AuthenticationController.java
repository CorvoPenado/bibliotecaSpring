package com.livros.biblioteca.controllers;

import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.LoginDTO;
import com.livros.biblioteca.recorders.LoginResponseDTO;
import com.livros.biblioteca.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; // Injeta o novo serviço

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        var token = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        Authentication authentication = authenticationManager.authenticate(token);

        // GERA O TOKEN JWT
        String jwtToken = jwtService.gerarToken(authentication);

        // Retorna o token dentro de um objeto JSON
        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }
}
