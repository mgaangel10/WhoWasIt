package com.example.WhoWasIts.Cuestionario.Repositorio;

import com.example.WhoWasIts.Cuestionario.model.Opciones;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OpcionesRepo extends JpaRepository<Opciones, UUID> {
}
