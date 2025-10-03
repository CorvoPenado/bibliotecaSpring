package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Autor;
import com.livros.biblioteca.models.Copia;
import com.livros.biblioteca.models.Genero;
import com.livros.biblioteca.models.Livro;
import com.livros.biblioteca.recorders.*;
import com.livros.biblioteca.repositorys.AutorRepository;
import com.livros.biblioteca.repositorys.CopiaRepository;
import com.livros.biblioteca.repositorys.GeneroRepository;
import com.livros.biblioteca.repositorys.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //Resolve o caso da conexão fechar antes de concluir a requisição
    //o @Transactional(readOnly = true) diz: Execute isso e só fecha quando terminar tudo
    //Prático e funcional :3
    @Transactional(readOnly = true)
    public List<DetalhesLivrosDTO> getLivros(){
        List<Livro> livros = livroRepository.findAll();

        return livros.stream().map(livro -> new DetalhesLivrosDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getSinopse(),
                livro.getAutor().getNome(),
                livro.getGenero().getNome()
        )).toList();
    }

}
