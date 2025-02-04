package com.example.WhoWasIts.Postear.Repositorio;

import com.example.WhoWasIts.Postear.model.Provincias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProvinciasRepo extends JpaRepository<Provincias, UUID> {


}
