package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Autor;
import com.livros.biblioteca.models.Copia;
import com.livros.biblioteca.models.Genero;
import com.livros.biblioteca.models.Livro;
import com.livros.biblioteca.recorders.CopiaCreateRequestDTO;
import com.livros.biblioteca.recorders.DetalhesLivrosAutorDTO;
import com.livros.biblioteca.recorders.DetalhesLivrosGeneroDTO;
import com.livros.biblioteca.recorders.LivroCreateRequestDTO;
import com.livros.biblioteca.repositorys.AutorRepository;
import com.livros.biblioteca.repositorys.CopiaRepository;
import com.livros.biblioteca.repositorys.GeneroRepository;
import com.livros.biblioteca.repositorys.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final GeneroRepository generoRepository;
    private final CopiaRepository copiaRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository, GeneroRepository generoRepository,CopiaRepository copiaRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
        this.copiaRepository = copiaRepository;
    }

    public Livro livroCreate(LivroCreateRequestDTO livro){
        Genero genero = generoRepository.findById(livro.generoId())
                .orElseThrow(() -> new RuntimeException("Genero não encontrado com o ID: " + livro.generoId()));

        Autor autor = autorRepository.findById(livro.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + livro.autorId()));

        Livro newLivro = new Livro();
        newLivro.setTitulo(livro.titulo());
        newLivro.setSinopse(livro.sinopse());
        newLivro.setAutor(autor);
        newLivro.setGenero(genero);
        Livro savedLivro = livroRepository.save(newLivro);

        Copia copia = new Copia();

        copia.setLivro(savedLivro);

        copiaRepository.save(copia);

        return savedLivro;
    }

    /*
    @Transactional
    public void deletarLivro(Long id){
        livroRepository.deleteById(id);
    }
    */

    public List<DetalhesLivrosAutorDTO> encontrarLivrosAutor(String nomeAutor){
        return livroRepository.detalheAutorLivros(nomeAutor);
    }

    public List<DetalhesLivrosGeneroDTO> encontrarLivrosGenero(String nomeGenero){
        return livroRepository.detalheGeneroLivros(nomeGenero);
    }

    public List<Livro> getLivros(){
        return livroRepository.findAll();
    }

}
