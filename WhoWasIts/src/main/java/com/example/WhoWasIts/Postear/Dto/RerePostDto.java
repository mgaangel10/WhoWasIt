package com.example.WhoWasIts.Postear.Dto;

import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;
import com.example.WhoWasIts.Postear.model.Postear;
import org.apache.catalina.LifecycleState;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record RerePostDto(UUID id,
                          String contenido,
                          String nombreUsuario,
                          String foto,
                          int numeroMegustas,
                          List<ComentariosDto> comentariosDtos,
                          PostDto postDto) {
    public static RerePostDto of (Postear p){
        return new RerePostDto(
                p.getId(),
                p.getContenido(),
                p.getUsuarioAnonimo().getNombreDelUsuario(),
                p.getUsuarioAnonimo().getAvatar().getImagen(),
                p.getFavoritoList()==null ? 0 : p.getFavoritoList().size(),
                p.getComentarios()== null ? null : p.getComentarios().stream().map(ComentariosDto::of).collect(Collectors.toList()),
                PostDto.of(p.getPostears())
        );
    }
}
