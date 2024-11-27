package com.example.WhoWasIts.UsuarioAnonimo.repositorio;

import com.example.WhoWasIts.UsuarioAnonimo.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AvatarRepo extends JpaRepository<Avatar, UUID> {

    Optional<Avatar> findByImagen(String imagen);
}
