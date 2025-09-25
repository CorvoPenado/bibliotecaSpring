package com.livros.biblioteca.repositorys;

import com.livros.biblioteca.models.Livro;
import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.DetalhesLivrosAutorDTO;
import com.livros.biblioteca.recorders.DetalhesLivrosGeneroDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("""
       
       SELECT new com.livros.biblioteca.recorders.DetalhesLivrosAutorDTO(
       a.nome,
       l.titulo
       )
       
       FROM Livro l
       JOIN l.autor a
       WHERE a.nome = :nomeAutor
        
    """)
    List<DetalhesLivrosAutorDTO> detalheAutorLivros(@Param("nomeAutor") String nome);

    @Query("""
        SELECT new com.livros.biblioteca.recorders.DetalhesLivrosGeneroDTO(
       a.nome,
       l.titulo,
       g.nome
       )
       FROM Livro l
       JOIN l.autor a
       JOIN l.genero g
       WHERE g.nome = :nomeGenero      
    """)
    List<DetalhesLivrosGeneroDTO> detalheGeneroLivros(@Param("nomeGenero") String nome);
    boolean existsByAutorId(Long id);
}
