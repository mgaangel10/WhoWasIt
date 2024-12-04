package com.example.WhoWasIts.Cuestionario.Repositorio;

import com.example.WhoWasIts.Cuestionario.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VotoRepo extends JpaRepository<Voto, UUID> {
}
