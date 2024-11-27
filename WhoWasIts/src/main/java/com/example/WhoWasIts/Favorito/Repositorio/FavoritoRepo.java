package com.example.WhoWasIts.Favorito.Repositorio;

import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.Favorito.model.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FavoritoRepo extends JpaRepository<Favorito, FavoritoId> {

    Optional<Favorito> findByUsuarioIdAndPostearId(UUID usuarioId,UUID postearId);
}
