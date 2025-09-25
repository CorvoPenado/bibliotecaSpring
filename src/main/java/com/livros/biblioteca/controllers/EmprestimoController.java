package com.livros.biblioteca.controllers;

import com.livros.biblioteca.models.Emprestimo;
import com.livros.biblioteca.recorders.CopiaQuantidadePatchDTO;
import com.livros.biblioteca.recorders.DetalhesEmprestimoUsuarioDTO;
import com.livros.biblioteca.recorders.EmprestimoCreateRequestDTO;
import com.livros.biblioteca.recorders.EmprestimoDevolucaoDTO;
import com.livros.biblioteca.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Emprestimo> emprestimoCreate(@Validated @RequestBody EmprestimoCreateRequestDTO emprestimo){
        Emprestimo createEmprestimo = emprestimoService.emprestimoCreate(emprestimo);

        return new ResponseEntity<>(createEmprestimo, HttpStatus.CREATED);
    }



    @GetMapping("/lista")
    public List<Emprestimo> getAllEmprestimos(){
        return emprestimoService.getEmprestimos();
    }

    @GetMapping("/detalhes")
    public ResponseEntity<List<DetalhesEmprestimoUsuarioDTO>> obterDetalhesEmprestimo(){
        List<DetalhesEmprestimoUsuarioDTO> emprestimo = emprestimoService.listarDetalhesDeEmprestimo();
        return ResponseEntity.ok(emprestimo);
    }

    @PatchMapping("/{emprestimoId}/devolver")
    public ResponseEntity<Void> devolverEmprestimo(
            @PathVariable Long emprestimoId,
            @Validated @RequestBody EmprestimoDevolucaoDTO devolucaoDTO) {

        emprestimoService.finalizarEmprestimo(emprestimoId, devolucaoDTO);

        return ResponseEntity.noContent().build();
    }

}
