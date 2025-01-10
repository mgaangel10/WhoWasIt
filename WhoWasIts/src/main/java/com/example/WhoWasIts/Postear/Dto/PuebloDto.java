package com.example.WhoWasIts.Postear.Dto;

import com.example.WhoWasIts.Postear.model.Pueblos;

import java.util.UUID;

public record PuebloDto(UUID idPueblo,
                        String nombrePueblo) {
    public static PuebloDto of (Pueblos p){
        return new PuebloDto(
                p.getId(),
                p.getPueblos()
        );
    }
}
