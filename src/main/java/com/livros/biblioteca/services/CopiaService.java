package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Copia;
import com.livros.biblioteca.recorders.CopiaQuantidadePatchDTO;
import com.livros.biblioteca.recorders.DetalhesCopiaDTO;
import com.livros.biblioteca.repositorys.CopiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopiaService {

    private final CopiaRepository copiaRepository;

    @Autowired
    public CopiaService(CopiaRepository copiaRepository) {
        this.copiaRepository = copiaRepository;
    }

    public List<DetalhesCopiaDTO> listarDetalhesDeCopias(){
        return copiaRepository.findDetalhesDeTodasAsCopias();
    }

    @Transactional
    public Copia atualizarAquantidade(Long id, CopiaQuantidadePatchDTO dto){
        Copia  copiaExistente = copiaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cópia não encontrada do ID: " + id));

        //Verifica se não é NULO, se não for, só guardo a nova quantidade por conveniencia
        //Verifico se é negativo, depois se a quantidade for maior que 0, atualiza status
        //caso contrário atualiza status pra indisponível. simples
        if(dto.qtd() != null){
            Integer novaQuantidade = dto.qtd();

            if(novaQuantidade < 0 ){
                throw new IllegalArgumentException("A quantidade não pode ser um valor negativo.");
            }

            copiaExistente.setQuantidade(copiaExistente.getQuantidade() + novaQuantidade);

            if (novaQuantidade > 0) {
                copiaExistente.setStatus("DISPONÍVEL");
            } else {
                copiaExistente.setStatus("INDISPONÍVEL");
            }

        }

        return copiaExistente;
    }

}
