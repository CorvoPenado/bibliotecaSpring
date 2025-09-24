package com.livros.biblioteca.recorders;

import java.time.LocalDateTime;

public record EmprestimoCreateRequestDTO(LocalDateTime dataTermino,Integer quantidade,Long usuarioId,Long copiaId) {
}
