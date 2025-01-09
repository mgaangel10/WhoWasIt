package com.example.WhoWasIts.Favorito.Dto;

import com.example.WhoWasIts.Favorito.model.Favorito;

public record DetallesFavoritosDto(String nombreUsuario,
                                   String foto) {
    public static DetallesFavoritosDto of(Favorito f){
        return new DetallesFavoritosDto(
                f.getUsuario().getUsuarioAnonimo().getNombreDelUsuario(),
                f.getUsuario().getUsuarioAnonimo().getAvatar().getImagen()
        );
    }
}
