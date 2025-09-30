package com.livros.biblioteca.services;

import com.livros.biblioteca.models.Copia;
import com.livros.biblioteca.models.Emprestimo;
import com.livros.biblioteca.models.Usuario;
import com.livros.biblioteca.recorders.CopiaQuantidadePatchDTO;
import com.livros.biblioteca.recorders.DetalhesEmprestimoUsuarioDTO;
import com.livros.biblioteca.recorders.EmprestimoCreateRequestDTO;
import com.livros.biblioteca.recorders.EmprestimoDevolucaoDTO;
import com.livros.biblioteca.repositorys.CopiaRepository;
import com.livros.biblioteca.repositorys.EmprestimoRepository;
import com.livros.biblioteca.repositorys.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoReposistory;
    private final UsuarioRepository usuarioReposistory;
    private final CopiaRepository copiaRepository;
    private final CopiaService copiaService;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoReposistory, UsuarioRepository usuarioReposistory, CopiaRepository copiaRepository, CopiaService copiaService) {
        this.emprestimoReposistory = emprestimoReposistory;
        this.usuarioReposistory = usuarioReposistory;
        this.copiaRepository = copiaRepository;
        this.copiaService = copiaService;
    }


    public Emprestimo emprestimoCreate(EmprestimoCreateRequestDTO emprestimo){

        Usuario usuario = usuarioReposistory.findById(emprestimo.usuarioId())
                .orElseThrow(()-> new RuntimeException("Usuario não encontrado do ID: " + emprestimo.usuarioId()));

        Copia copia = copiaRepository.findById(emprestimo.copiaId()).
                orElseThrow(()-> new RuntimeException("Copia não encontrada do ID: " + emprestimo.copiaId()));

        //Validações
        if(copia.getStatus().equals("INDISPONÍVEL")){
            throw new IllegalStateException("Cópia Indisponível - Quantidade insuficiente");
        }

        if(copia.getQuantidade() < emprestimo.quantidade()){
            throw new IllegalArgumentException("Quantidade solicitada superior à disponível.");
        }
        //Fim Validações

        Emprestimo newEmprestimo = new Emprestimo();

        newEmprestimo.setDataEmprestimoTermino(emprestimo.dataTermino());
        newEmprestimo.setQuantidade(emprestimo.quantidade());
        newEmprestimo.setUsuario(usuario);
        newEmprestimo.setCopia(copia);

        Integer quantidadeAtual = copia.getQuantidade();
        Integer novaQuantidade = quantidadeAtual - emprestimo.quantidade();

        copia.setQuantidade(novaQuantidade);

        if(novaQuantidade <= 0){
            copia.setStatus("INDISPONÍVEL");
        }

        copiaRepository.save(copia);

        return emprestimoReposistory.save(newEmprestimo);
    }

    @Transactional
    public void finalizarEmprestimo(Long emprestimoId, EmprestimoDevolucaoDTO dto){
        Emprestimo emprestimo = emprestimoReposistory.findById(emprestimoId)
                .orElseThrow(()-> new RuntimeException("Não encontrado Usuario do ID: " + emprestimoId));

        Long usuarioId = dto.usuarioId();
        Integer qtdDevolvida = dto.quantidadeDevolvida();

        if(!emprestimo.getUsuario().getId().equals(usuarioId)){
            throw new SecurityException("Acesso negado: Este empréstimo não pertence ao usuário informado.");
        }
        if(qtdDevolvida <= 0){
            throw new IllegalArgumentException("A quantidade a ser devolvida deve ser um número positivo!");
        }
        if(emprestimo.getQuantidade() < qtdDevolvida){
            throw new IllegalArgumentException("A quantidade devolvida é SUPERIOR à quantidade emprestada.");
        }

        Copia copia = emprestimo.getCopia();
        Integer novaQuantidadeEstoque = copia.getQuantidade() + qtdDevolvida;

        CopiaQuantidadePatchDTO patchDTO = new CopiaQuantidadePatchDTO(novaQuantidadeEstoque);

        copiaService.atualizarAquantidade(copia.getId(), patchDTO);

        int quantidadeRestante = emprestimo.getQuantidade() - qtdDevolvida;
        emprestimo.setQuantidade(quantidadeRestante);

        if (quantidadeRestante == 0) {
            emprestimo.setFinalizado(true);
            emprestimo.setEmDia(true);
            emprestimo.setMulta(0);
            emprestimo.setDataFinalizadoEmprestimo(LocalDateTime.now());
        }

    }

    public List<Emprestimo> getEmprestimos(){
        return emprestimoReposistory.findAll();
    }

    public List<DetalhesEmprestimoUsuarioDTO> listarDetalhesDeEmprestimo(){
        return emprestimoReposistory.findDetalhesTodosEmprestimos();
    }

}
