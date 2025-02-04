package com.example.WhoWasIts.Lugares.Dto;

import com.example.WhoWasIts.Lugares.model.Lugares;

import java.util.UUID;

public record LugaresDto (UUID id,
                          String nombreLugar) {

    public static LugaresDto of (Lugares l){
        return new
                LugaresDto(
                        l.getId(),
                l.getNombreLugar()
        );
    }
}
