package io.github.cursospring.libraryapi.controller.dto;

import io.github.cursospring.libraryapi.model.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        @ISBN(message = "ISBN inválido.")
        @NotBlank(message = "campo obrigatorio.")
        String isbn,
        @NotBlank(message = "campo obrigatorio.")
        String titulo,
        @NotNull(message = "campo obrigatorio.")
        @Past(message = "a data nao pode ser superior a data atual.")
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        @NotNull(message = "campo obrigatorio.")
        UUID idAutor
)
{

}
