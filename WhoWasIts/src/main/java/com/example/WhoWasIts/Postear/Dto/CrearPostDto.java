package com.example.WhoWasIts.Postear.Dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record CrearPostDto(String contenido,
                           String lugar,

                           UUID id,
                           UUID idCuestionario,
                           Boolean postUnaVez,
                           Boolean desorden


                           ) {
}
