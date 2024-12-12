package com.example.WhoWasIts.FavoritoCharla.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavoritoIdCharla implements Serializable {
    private UUID usuarioId;
    private UUID charlaId;
}
