package com.example.WhoWasIts.Favorito.model;

import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoritoId implements Serializable {

    private UUID usuarioId;
    private UUID postearId;
}
