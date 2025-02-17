package com.example.WhoWasIts.FlashPost.Dto;

import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;
import com.example.WhoWasIts.FlashPost.model.Visualizacion;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record VisualizacionDto(UUID idVisualizacion,
                               UUID idPost,
                               UUID idUsuario,
                               String foto,
                               String nombreUsuario,
                               String tiempoPublicado,
                               String contenido,
                               String menciones,
                               List<ComentariosDto> comentariosDtos,
                               int numeroDeMegustas,
                               int numerosDeComentarios,
                               UsarioVeFlashPostDto usarioVeFlashPostDto
                               ) {
    public static VisualizacionDto of(Visualizacion v){
        return new VisualizacionDto(
                v.getId(),
                v.getPostear().getId(),
                v.getUsuario().getId(),
                v.getPostear().getUsuarioAnonimo().getAvatar().getImagen(),
                v.getUsuario().getUsuarioAnonimo().getNombreDelUsuario(),
                v.getPostear().getTiempoPublicado(),
                v.getPostear().getContenido(),
                v.getPostear().getMenciones(),
                v.getPostear().getComentarios().stream().map(ComentariosDto::of).collect(Collectors.toList()),
                v.getPostear().getComentarios() == null ? 0 : v.getPostear().getComentarios().size(),
                v.getPostear().getFavoritoList() == null ? 0 : v.getPostear().getFavoritoList().size(),
                UsarioVeFlashPostDto.of(true)


        );
    }
}
