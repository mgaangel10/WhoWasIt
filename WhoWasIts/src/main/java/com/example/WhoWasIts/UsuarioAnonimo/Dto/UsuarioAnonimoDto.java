package com.example.WhoWasIts.UsuarioAnonimo.Dto;

import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import com.example.WhoWasIts.users.model.Usuario;

import java.util.UUID;

public record UsuarioAnonimoDto(UUID id,
                                String nombreUsuario,
                                String foto,
                                Usuario usuario) {
    public static UsuarioAnonimoDto of(UsuarioAnonimo u){
        return new UsuarioAnonimoDto(
                u.getId(),
                u.getNombreDelUsuario(),
                u.getAvatar().getImagen(),
                u.getUsuario()
        );
    }
}
