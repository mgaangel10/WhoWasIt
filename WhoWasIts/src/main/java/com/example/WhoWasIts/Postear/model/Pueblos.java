package com.example.WhoWasIts.Postear.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Pueblos {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;

    private String pueblos;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincias provincia;
}
