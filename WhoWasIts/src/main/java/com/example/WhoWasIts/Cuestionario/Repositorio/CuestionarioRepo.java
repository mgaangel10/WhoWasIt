package com.example.WhoWasIts.Cuestionario.Repositorio;

import com.example.WhoWasIts.Cuestionario.model.Cuestionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CuestionarioRepo extends JpaRepository<Cuestionario, UUID> {
    void deleteByOpcionesId(UUID opcionesId);

}
