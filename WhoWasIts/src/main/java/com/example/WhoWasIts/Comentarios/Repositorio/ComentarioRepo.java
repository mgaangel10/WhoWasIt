package com.example.WhoWasIts.Comentarios.Repositorio;

import com.example.WhoWasIts.Charlas.model.Charlas;
import com.example.WhoWasIts.Comentarios.model.Comentario;
import com.example.WhoWasIts.Postear.model.Postear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ComentarioRepo extends JpaRepository<Comentario, UUID> {

    List<Comentario> findByPostear(Postear postear);
    List<Comentario> findByCharla(Charlas postear);

}
