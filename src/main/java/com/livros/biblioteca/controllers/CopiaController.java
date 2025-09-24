package com.livros.biblioteca.controllers;

import com.livros.biblioteca.models.Copia;
import com.livros.biblioteca.recorders.CopiaQuantidadePatchDTO;
import com.livros.biblioteca.recorders.DetalhesCopiaDTO;
import com.livros.biblioteca.services.CopiaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/copias")
public class CopiaController {

    private final CopiaService copiaService;

    @Autowired
    public CopiaController(CopiaService copiaService) {
        this.copiaService = copiaService;
    }

    @GetMapping("/detalhes")
    public ResponseEntity<List<DetalhesCopiaDTO>> obterTodosDetalhes(){
        List<DetalhesCopiaDTO> detalhes = copiaService.listarDetalhesDeCopias();
        return ResponseEntity.ok(detalhes);
    }

    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<Copia> atualizarQuantidadeCopia(@PathVariable Long id, @Validated @RequestBody CopiaQuantidadePatchDTO dto){
        Copia copiaAtualizada = copiaService.atualizarAquantidade(id,dto);
        return ResponseEntity.ok(copiaAtualizada);
    }

}
