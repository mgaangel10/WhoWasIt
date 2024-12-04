package com.example.WhoWasIts.Cuestionario.Dto;

import com.example.WhoWasIts.Cuestionario.model.Opciones;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record OpcionesDto(UUID id,
                          String opcion,
                          UUID idCuestionario,
                          List<VotarDto> votarDtos,
                          int numeroVotos,
                          int porcentajeVotos) {
    public static OpcionesDto of(Opciones o){
        return new OpcionesDto(
                o.getId(),
                o.getOpcion(),
                o.getCuestionario().getId(),
                o.getVotos()== null ? null: o.getVotos().stream().map(VotarDto::of).collect(Collectors.toList()),
                o.getNumeroVotos(),
                o.getPorcentajeVotos()
        );
    }
}
