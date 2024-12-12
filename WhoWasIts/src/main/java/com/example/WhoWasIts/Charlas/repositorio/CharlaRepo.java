package com.example.WhoWasIts.Charlas.repositorio;

import com.example.WhoWasIts.Charlas.model.Charlas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CharlaRepo extends JpaRepository<Charlas, UUID> {
}
