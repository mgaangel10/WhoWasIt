package com.example.WhoWasIts.Cuestionario.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

public class Opciones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String opcion;

    @ManyToOne
    @JsonBackReference
    private Cuestionario cuestionario;

    @OneToMany
    private List<Voto> votos;

    private int numeroVotos;
    private int porcentajeVotos;
}
