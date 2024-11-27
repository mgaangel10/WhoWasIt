package com.example.WhoWasIts.Comentarios.Dto;

import com.example.WhoWasIts.Comentarios.model.Comentario;

import java.util.UUID;

public record ComentariosDto(UUID id,
                             String contenido,
                             String nombreUsuario,
                             String foto) {
    public static ComentariosDto of(Comentario c){
        return new ComentariosDto(
                c.getId(),
                c.getContenido(),
                c.getUsuario().getUsuarioAnonimo().getNombreDelUsuario(),
                c.getUsuario().getUsuarioAnonimo().getAvatar().getImagen()
        );
    }
}
