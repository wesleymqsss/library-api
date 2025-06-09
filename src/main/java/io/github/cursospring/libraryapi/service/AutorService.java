package io.github.cursospring.libraryapi.service;

import io.github.cursospring.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.cursospring.libraryapi.model.Autor;
import io.github.cursospring.libraryapi.repository.AutorRepository;
import io.github.cursospring.libraryapi.repository.LivroRepository;
import io.github.cursospring.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor //adicao dos poderes do lombok para injecao de dependencias
public class AutorService {

    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;


    public Autor salvar(Autor autor){
        validator.validar(autor);
        return repository.save(autor);
    }

    public void atualizar(Autor autor){
        if (autor.getId() == null){
            throw new IllegalArgumentException("Para atualizar é necessario que o autor esteja salvo na base.");
        }
        validator.validar(autor);
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar (Autor autor){
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Não é permitido excluir autor que possui livros cadastrados.");
        }
        repository.delete(autor);
    }

    //novo metodo de pesquisa dinamica
    public List<Autor> pesquisaByExample(String nome, String nacionalidade){
        var auto =  new Autor();
        auto.setNome(nome);
        auto.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnorePaths("id", "dataNascimento")//simulando se o objeto vinhesse completo, podemos ignorar parametros de pesquisa
                .withIgnoreNullValues()//ignora valores nulos
                .withIgnoreCase()//ignora se a paravra esta maiuscula ou minuscula
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //pesquisa na coluna o fim, inicio ou meio da palavra. como se fosse o %Like% do mysql

        Example<Autor> autorExample = Example.of(auto, matcher);

        return repository.findAll(autorExample);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null){
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if(nome != null){
            return repository.findByNome(nome);
        }

        if(nacionalidade != null){
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();
    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
