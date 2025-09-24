package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Autor;
import com.livros.biblioteca.recorders.AutorCreateRequestDTO;
import com.livros.biblioteca.repositorys.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
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

}
