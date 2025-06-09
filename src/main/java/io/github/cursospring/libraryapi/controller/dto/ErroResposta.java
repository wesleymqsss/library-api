package io.github.cursospring.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros){

    public static ErroResposta respostaPadrao(String mensagem){
        return  new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
    }

    public static ErroResposta confilito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }
}
