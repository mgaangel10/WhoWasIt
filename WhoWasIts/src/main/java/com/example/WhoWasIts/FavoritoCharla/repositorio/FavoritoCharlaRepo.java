package com.example.WhoWasIts.FavoritoCharla.repositorio;

import com.example.WhoWasIts.FavoritoCharla.model.FavoritoCharla;
import com.example.WhoWasIts.FavoritoCharla.model.FavoritoIdCharla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FavoritoCharlaRepo extends JpaRepository<FavoritoCharla, FavoritoIdCharla> {

    Optional<FavoritoCharla> findByUsuarioIdAndCharlaId(UUID usuarioId, UUID charlaId);
}
