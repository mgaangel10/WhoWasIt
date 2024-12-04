package com.example.WhoWasIts.Cuestionario.Dto;

import com.example.WhoWasIts.Cuestionario.model.Voto;
import com.example.WhoWasIts.users.Dto.GetUsuario;

import java.util.UUID;

public record VotarDto(UUID idCuestionario,

        GetUsuario getUsuario) {
    public static VotarDto of(Voto v){
        return new VotarDto(
                v.getOpciones().getCuestionario().getId(),
                GetUsuario.of(v.getUsuario())
        );
    }
}
