package com.livros.biblioteca.config;

import com.livros.biblioteca.models.*;
import com.livros.biblioteca.repositorys.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AutorRepository autorRepository;
    private final GeneroRepository generoRepository;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final CopiaRepository copiaRepository;
    private final PasswordEncoder passwordEncoder;

    // Injete todos os repositórios
    public DataInitializer(AutorRepository autorRepository, GeneroRepository generoRepository, LivroRepository livroRepository, UsuarioRepository usuarioRepository, CopiaRepository copiaRepository, PasswordEncoder passwordEncoder) {
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.copiaRepository = copiaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Crie e salve um autor
        Autor autor = new Autor();
        autor.setNome("Machado de Assis");
        autor.setEmail("machado@abl.com.br");
        autorRepository.save(autor);

        // Crie e salve um gênero
        Genero genero = new Genero();
        genero.setNome("Romance");
        generoRepository.save(genero);

        // Crie um usuário
        Usuario usuario = new Usuario();
        usuario.setNome("Nicolly Barros");
        usuario.setEmail("nick.bl@email.com");
        usuario.setSenha(passwordEncoder.encode("123"));
        usuario.setRole(Role.ADMIN);
        usuario.setIdade(25);
        usuarioRepository.save(usuario);

        // Crie um livro, relacionando com o autor e gênero já salvos
        Livro livro = new Livro();
        livro.setTitulo("Dom Casmurro");
        livro.setSinopse("Uma história de ciúmes e incertezas.");
        livro.setAutor(autor);
        livro.setGenero(genero);
        livroRepository.save(livro);

        // Crie uma cópia, relacionando com o livro
        Copia copia = new Copia();
        copia.setStatus("DISPONÍVEL");
        copia.setQuantidade(5);
        copia.setLivro(livro);
        copiaRepository.save(copia);

        System.out.println(">>> Dados iniciais inseridos com sucesso! <<<");
    }
}