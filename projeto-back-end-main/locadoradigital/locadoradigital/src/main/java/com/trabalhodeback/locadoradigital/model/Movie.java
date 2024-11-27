package com.trabalhodeback.locadoradigital.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "Movie")
@Data

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String produtora;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Integer anoLançamento;

    @Column(nullable = false)
    private Integer numeroEstoque;


}
