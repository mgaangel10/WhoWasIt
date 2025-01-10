package com.example.WhoWasIts.Postear.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Provincias {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;

    private String provincia;
    @JsonIgnore
    @OneToMany(mappedBy = "provincia")
    private List<Pueblos> pueblos;



}
