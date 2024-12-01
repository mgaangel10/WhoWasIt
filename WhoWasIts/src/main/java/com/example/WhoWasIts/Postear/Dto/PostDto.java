package com.example.WhoWasIts.Postear.Dto;

import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;
import com.example.WhoWasIts.Postear.model.Postear;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PostDto(UUID id,
                      String contenido,
                      String nombreUsuario,
                      String foto,
                      String tiempoPublicado,
                      String meciones,
                      int numeroMegustas,
                      List<ComentariosDto> comentariosDtos,
                      PostDto ps
                      ) {

    public static PostDto of (Postear p){

        return new PostDto(
                p.getId(),
                p.getContenido(),
                p.getUsuarioAnonimo().getNombreDelUsuario(),
                p.getUsuarioAnonimo().getAvatar().getImagen(),
                p.getTiempoPublicado(),
                p.getMenciones(),
                p.getFavoritoList()==null ? 0 : p.getFavoritoList().size(),
                p.getComentarios()== null ? null : p.getComentarios().stream().map(ComentariosDto::of).collect(Collectors.toList()),
                p.getPostears() == null ? null:PostDto.of(p.getPostears()) // Manejo de repost
        );
    }
}
