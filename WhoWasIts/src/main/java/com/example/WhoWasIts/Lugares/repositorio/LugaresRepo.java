package com.example.WhoWasIts.Lugares.repositorio;

import com.example.WhoWasIts.Lugares.model.Lugares;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LugaresRepo extends JpaRepository<Lugares, UUID> {

    Optional<Lugares> findByNombreLugar(String nombre);
}
