package com.example.WhoWasIts.Postear.Dto;

import com.example.WhoWasIts.Postear.model.Provincias;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ProvinciasDto(UUID idProvincia,
                            String provincia,
                            List<PuebloDto> puebloDtos) {

    public static ProvinciasDto of (Provincias p){
        return new ProvinciasDto(
                p.getId(),
                p.getProvincia(),
                p.getPueblos().stream().map(PuebloDto::of).collect(Collectors.toList())
        );
    }
}
