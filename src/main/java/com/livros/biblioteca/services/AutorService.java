package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Autor;
import com.livros.biblioteca.recorders.AutorCreateRequestDTO;
import com.livros.biblioteca.repositorys.AutorRepository;
import com.livros.biblioteca.repositorys.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public Autor autorCreate(AutorCreateRequestDTO autor){
        Autor newAutor = new Autor();

        newAutor.setNome(autor.nome());
        newAutor.setEmail(autor.email());

        return autorRepository.save(newAutor);
    }

    public List<Autor> getAutores(){
        return autorRepository.findAll();
    }

    @Transactional
    public void deletarAutor(Long id){
        if(!autorRepository.existsById(id)){
            throw new RuntimeException("Não foi possível achar um autor com o id: " + id);
        }

        if(!livroRepository.existsByAutorId(id)){
            throw new RuntimeException("Não é possível deletar o autor, pois ele ainda possui livros no sistema.");
        }

        autorRepository.deleteById(id);
    }

}
