package com.livros.biblioteca.repositorys;

import com.livros.biblioteca.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
