package com.example.WhoWasIts.Postear.Repositorio;

import com.example.WhoWasIts.Postear.model.Pueblos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PueblosRepo extends JpaRepository<Pueblos, UUID> {
}
