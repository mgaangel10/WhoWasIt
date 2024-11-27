package com.example.WhoWasIts.UsuarioAnonimo.service;

import com.example.WhoWasIts.UsuarioAnonimo.Dto.CrearUsuarioAnoninoDto;
import com.example.WhoWasIts.UsuarioAnonimo.Dto.UsuarioAnonimoDto;
import com.example.WhoWasIts.UsuarioAnonimo.model.Avatar;
import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import com.example.WhoWasIts.UsuarioAnonimo.repositorio.AvatarRepo;
import com.example.WhoWasIts.UsuarioAnonimo.repositorio.UsuarioAnonimoRepo;
import com.example.WhoWasIts.users.model.Administrador;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioAnonimoService {

    private final UsuarioAnonimoRepo usuarioAnonimoRepo;
    private final AvatarRepo avatarRepo;
    private final UsuarioRepo usuarioRepo;

    public UsuarioAnonimoDto crearUsuarioAnonimo(CrearUsuarioAnoninoDto crearUsuarioAnoninoDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<UsuarioAnonimo> usuarioAnonimo1 = usuarioAnonimoRepo.findByNombreDelUsuarioIgnoreCase(crearUsuarioAnoninoDto.nombreUsuario());
            if (usuarioAnonimo1.isPresent()){
                throw new RuntimeException("ya existe ese nombre de usuario");
            }
            Optional<Avatar> avatar = avatarRepo.findByImagen(crearUsuarioAnoninoDto.foto());
            if (usuario.isPresent() && avatar.isPresent()){
                UsuarioAnonimo usuarioAnonimo = new UsuarioAnonimo();
                usuarioAnonimo.setNombreDelUsuario(crearUsuarioAnoninoDto.nombreUsuario());
                usuarioAnonimo.setAvatar(avatar.get());
                usuarioAnonimo.setUsuario(usuario.get());
                usuarioAnonimoRepo.save(usuarioAnonimo);
                usuario.get().setUsuarioAnonimo(usuarioAnonimo);
                usuarioRepo.save(usuario.get());
                return UsuarioAnonimoDto.of(usuarioAnonimo);
            }
        }

        return null;
    }



}
