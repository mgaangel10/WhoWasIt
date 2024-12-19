package com.example.WhoWasIts.FlashPost.repositorio;

import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.FlashPost.model.Visualizacion;
import com.example.WhoWasIts.users.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VisualizacionRepo extends JpaRepository<Visualizacion, UUID> {

    Optional<Visualizacion> findByUsuarioAndPostear(Usuario usuario, Postear publicacion);

}
