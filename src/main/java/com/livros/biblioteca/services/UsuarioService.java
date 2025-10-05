package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Role;
import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.UsuarioCreateRequestDTO;
import com.livros.biblioteca.repositorys.EmprestimoRepository;
import com.livros.biblioteca.repositorys.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, EmprestimoRepository emprestimoRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario usuarioCreate(UsuarioCreateRequestDTO user){

        Usuario newUser = new Usuario();

        newUser.setNome(user.nome());
        newUser.setEmail(user.email());
        newUser.setIdade(user.idade());
        newUser.setSenha(passwordEncoder.encode(user.senha()));
        newUser.setRole(Role.USER);

        return usuarioRepository.save(newUser);

    }

    @Transactional
    public void deletarUsuario(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontradao do ID: " + id);
        }

        if(emprestimoRepository.existsByUsuarioIdAndFinalizadoFalse(id)){
            throw new RuntimeException("Não é possível deletar o usuário, pois ele ainda possui empréstimos ativos (não finalizados).");
        }

        usuarioRepository.deleteById(id);

    }

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

}
