package io.github.cursospring.libraryapi.controller;

import io.github.cursospring.libraryapi.controller.dto.AutorDTO;
import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor){
        Autor autorEntidade = autor.mapearParaAutor();
        autorService.salvar(autorEntidade);

        // Construção da URI.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Pega a URI base da requisição atual (ex: http://localhost:8080/autores)
                .path("/{id}")      // Adiciona o template do path para o ID
                .buildAndExpand(autorEntidade.getId()) // Substitui {id} pelo valor real
                .toUri(); // Constrói a URI final

        return ResponseEntity.created(location).build();
    }
}
