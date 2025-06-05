package io.github.cursospring.libraryapi.model;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter
@Setter
public class Autor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento" , nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length =  50, nullable = false)
    private String nacionalidade;

}
