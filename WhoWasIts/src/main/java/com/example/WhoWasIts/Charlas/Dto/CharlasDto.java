package com.example.WhoWasIts.Charlas.Dto;

import com.example.WhoWasIts.Charlas.model.Charlas;
import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record CharlasDto(UUID id,
                         String nombreUsuario,
                         String fotourl,
                         String contenido,
                         String tiempoPublicado,
                         String meciones,
                         int numeroMegustas,
                         List<ComentariosDto> comentariosDtoLis
                         ) {
    public static CharlasDto of (Charlas c){
        return new CharlasDto(
                c.getId(),
                c.getUsuarioAnonimo().getNombreDelUsuario(),
                c.getUsuarioAnonimo().getAvatar().getImagen(),
                c.getContenido(),
                c.getTiempoPublicado(),
                c.getMenciones(),
                c.getFavoritoList() == null ? 0 : c.getFavoritoList().size(),
                c.getComentarios().stream().map(ComentariosDto::of).collect(Collectors.toList())

        );
    }
}
