package com.example.livro.apilivro.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRecordDto(
        @NotBlank String titulo,
        @NotBlank String autor,
        @NotBlank String isbn,
        @NotBlank String genero,
        @NotNull int anoPublicaco) {
}
