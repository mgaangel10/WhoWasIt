package com.example.WhoWasIts.UsuarioAnonimo.repositorio;

import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioAnonimoRepo extends JpaRepository<UsuarioAnonimo, UUID> {

    Optional<UsuarioAnonimo>findByNombreDelUsuarioIgnoreCase(String nombre);
}
