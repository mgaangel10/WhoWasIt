package com.example.WhoWasIts.FavoritoCharla.model;

import com.example.WhoWasIts.Charlas.model.Charlas;
import com.example.WhoWasIts.Favorito.model.FavoritoId;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FavoritoCharla {
    @EmbeddedId
    private FavoritoIdCharla id;

    @ManyToOne
    @MapsId("usuarioId")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @MapsId("charlaId")
    @JsonBackReference
    private Charlas charla;
}
