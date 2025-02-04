package com.example.WhoWasIts.Lugares.Dto;

import com.example.WhoWasIts.Lugares.model.Lugares;
import com.example.WhoWasIts.Postear.Dto.PuebloDto;
import com.example.WhoWasIts.Postear.model.Pueblos;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record LugaresAndPueblosDto(List<LugaresDto> lugares,
                                   List<PuebloDto> pueblos

                                   ) {
    public static LugaresAndPueblosDto of (List<LugaresDto> lugares , List<PuebloDto> pueblos){
        return new LugaresAndPueblosDto(
              lugares,
                pueblos
        );
    }
}
