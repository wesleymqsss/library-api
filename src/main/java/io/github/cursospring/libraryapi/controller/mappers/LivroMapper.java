package io.github.cursospring.libraryapi.controller.mappers;

import io.github.cursospring.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.cursospring.libraryapi.model.Livro;
import io.github.cursospring.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target="autor", expression="java( autorRepository.findById(dto.idAutor()).orElse(null) )" )
    public abstract Livro toEntity(CadastroLivroDTO dto);
}
