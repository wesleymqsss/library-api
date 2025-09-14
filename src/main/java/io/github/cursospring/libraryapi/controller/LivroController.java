package io.github.cursospring.libraryapi.controller;

import io.github.cursospring.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.cursospring.libraryapi.controller.dto.ErroResposta;
import io.github.cursospring.libraryapi.controller.mappers.LivroMapper;
import io.github.cursospring.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.cursospring.libraryapi.model.Livro;
import io.github.cursospring.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto){
        try{
            Livro livro = mapper.toEntity(dto);
            service.salvar(livro);

            return ResponseEntity.ok(livro);
        }catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.confilito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

}
