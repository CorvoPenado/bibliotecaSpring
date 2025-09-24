package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.UsuarioCreateRequestDTO;
import com.livros.biblioteca.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario usuarioCreate(UsuarioCreateRequestDTO user){

        Usuario newUser = new Usuario();

        newUser.setNome(user.nome());
        newUser.setEmail(user.email());
        newUser.setIdade(user.idade());

        return usuarioRepository.save(newUser);

    }

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

}
