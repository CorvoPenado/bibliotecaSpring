package com.livros.biblioteca.repositorys;

import com.livros.biblioteca.models.Copia;
import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.DetalhesCopiaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopiaRepository extends JpaRepository<Copia, Long> {

    @Query("""
    
        SELECT new com.livros.biblioteca.recorders.DetalhesCopiaDTO(
        c.id,
        l.titulo,
        g.nome,
        a.nome,
        c.status,
        c.quantidade
        )
        
        FROM Copia c
        
        JOIN c.livro l
        JOIN l.genero g
        JOIN l.autor a
      
    """)
    List<DetalhesCopiaDTO> findDetalhesDeTodasAsCopias();

}
