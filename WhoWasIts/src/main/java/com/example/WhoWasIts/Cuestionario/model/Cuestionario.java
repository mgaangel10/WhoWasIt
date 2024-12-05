package com.example.WhoWasIts.Cuestionario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String titulo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private  List<Opciones> opciones ;

    private int totalDeVotos;
}
