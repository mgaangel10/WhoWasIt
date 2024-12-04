package com.example.WhoWasIts.Cuestionario.Dto;

import com.example.WhoWasIts.Cuestionario.model.Cuestionario;
import com.example.WhoWasIts.Cuestionario.model.Opciones;
import com.example.WhoWasIts.Cuestionario.service.CuestionarioService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record CuestionarioDto(UUID id,
                              String titulo,
                              int totalVotos,
                              List<OpcionesDto> opciones) {

    public static CuestionarioDto of(Cuestionario c){


        return new CuestionarioDto(
                c.getId(),
                c.getTitulo(),
                c.getTotalDeVotos(),
                c.getOpciones() == null ? null : c.getOpciones().stream().map(OpcionesDto::of).collect(Collectors.toList())
        );
    }
}
