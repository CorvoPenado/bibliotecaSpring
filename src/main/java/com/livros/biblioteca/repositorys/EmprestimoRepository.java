package com.livros.biblioteca.repositorys;

import com.livros.biblioteca.models.Emprestimo;
import com.livros.biblioteca.recorders.DetalhesEmprestimoUsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    /*
    * Adicionar Get all emprestimos atrelados a ele mostrando Nome usuario,
    * Titulo do livro,quantidade, em dia,finalizazdo,multa e data de termino

    *String nomeUsuario, String tituloLivro, Integer quantidade,
    Boolean emDiaEmprestimo, Boolean FinalizadoEmprestimo, Integer MultaEmprestimo,
    LocalDateTime dataTerminoEmprestimo
     */

    @Query("""
        
        SELECT new com.livros.biblioteca.recorders.DetalhesEmprestimoUsuarioDTO(
            u.nome,
            l.titulo,
            e.quantidade,
            e.emDia,
            e.finalizado,
            e.multa,
            e.dataEmprestimoTermino
        )
        
        FROM Emprestimo e
        
        JOIN e.usuario u
        JOIN e.copia c
        JOIN c.livro l
            
    """)
    List<DetalhesEmprestimoUsuarioDTO> findDetalhesTodosEmprestimos();

    List<Emprestimo> findByFinalizadoFalseAndEmDiaTrue();

    boolean existsByUsuarioIdAndFinalizadoFalse(Long id);
}
