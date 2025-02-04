package com.example.WhoWasIts.Lugares.Dto;

import com.example.WhoWasIts.Postear.Dto.PuebloDto;
import com.example.WhoWasIts.Postear.model.Pueblos;

public record LugarAndPuebloDto(PuebloDto puebloDto,
                                LugaresDto lugaresDto) {
    public static LugarAndPuebloDto of(PuebloDto puebloDto, LugaresDto lugaresDto){
        return new LugarAndPuebloDto(
                puebloDto,
                lugaresDto
        );
    }
}
