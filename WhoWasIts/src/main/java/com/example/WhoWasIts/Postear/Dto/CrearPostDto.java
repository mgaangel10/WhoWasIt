package com.example.WhoWasIts.Postear.Dto;

import java.util.UUID;

public record CrearPostDto(String contenido,
                           UUID id,
                           UUID idCuestionario,
                           boolean postUnaVez,
                           boolean desorden


                           ) {
}
