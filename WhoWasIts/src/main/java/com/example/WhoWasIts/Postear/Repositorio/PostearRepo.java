package com.example.WhoWasIts.Postear.Repositorio;

import com.example.WhoWasIts.Postear.model.Postear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostearRepo extends JpaRepository<Postear, UUID> {
}
