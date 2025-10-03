package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class JwtService {
    //O que precisa ser entendido aqui é
    //Isso faz o trabalho de Gerenciar o TOKEN e o tempo que ele é valido. ponto padrão

    // Injeta a chave secreta do application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    // Gera o token JWT
    public String gerarToken(Authentication authentication) {
        Usuario principal = (Usuario) authentication.getPrincipal();
        Instant agora = LocalDateTime.now().toInstant(ZoneOffset.of("-03:00"));
        // Define o tempo de expiração do token (aqui, 2 horas)
        Instant expiracao = agora.plusSeconds(7200); // 2 horas * 3600 segundos

        return Jwts.builder()
                .setIssuer("API Biblioteca") // Quem está emitindo o token
                .setSubject(principal.getUsername()) // Quem é o usuário (no nosso caso, o email)
                .setIssuedAt(Date.from(agora)) // Data de emissão
                .setExpiration(Date.from(expiracao)) // Data de expiração
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Assina com o algoritmo e a chave secreta
                .compact(); // Compacta tudo em uma string
    }

    // Extrai o "subject" (usuário/email) do token
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}