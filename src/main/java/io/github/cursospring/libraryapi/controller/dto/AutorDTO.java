package io.github.cursospring.libraryapi.controller.dto;

import io.github.cursospring.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatorio")
        @Size(min = 2,max = 100, message = "campo fora do padrao")
        String nome,

        @NotNull(message = "Campo obrigatorio")
        @Past(message = "Data de nascimento nao pode ser maior ou igual a data atual.")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatorio")
        @Size(max = 50 , min = 2, message = "campo fora do padrao")
        String nacionalidade) {

    public Autor mapearParaAutor(){
        Autor autor =  new Autor();
        autor.setNome(this.nome);
        autor.setNacionalidade(this.nacionalidade);
        autor.setDataNascimento(this.dataNascimento);

        return autor;
    }
}
