package com.example.WhoWasIts.Comentarios.Dto;

import com.example.WhoWasIts.Comentarios.model.Comentario;
import com.example.WhoWasIts.Favorito.Dto.DetallesFavoritosDto;

public record DetallesDeComentariosDto(String nombreUsario,
                                       String foto) {
    public static DetallesDeComentariosDto of(Comentario c){
        return new DetallesDeComentariosDto(
                c.getUsuario().getUsuarioAnonimo().getNombreDelUsuario(),
                c.getUsuario().getUsuarioAnonimo().getAvatar().getImagen()
        );
    }
}
