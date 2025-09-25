package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Genero;
import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.GeneroCreateRequestDTO;
import com.livros.biblioteca.recorders.UsuarioCreateRequestDTO;
import com.livros.biblioteca.repositorys.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    @Autowired
    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero generoCreate(GeneroCreateRequestDTO genero){

        Genero newGenero = new Genero();

        newGenero.setNome(genero.nome());

        return generoRepository.save(newGenero);

    }

}
