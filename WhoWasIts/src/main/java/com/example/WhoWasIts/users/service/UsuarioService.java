package com.example.WhoWasIts.users.service;


import com.example.WhoWasIts.users.Dto.GetUsuario;
import com.example.WhoWasIts.users.Dto.PostCrearUserDto;
import com.example.WhoWasIts.users.Dto.PostLogin;
import com.example.WhoWasIts.users.model.UserRoles;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.AdministradorRepo;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdministradorRepo administradorRepo;


    public Optional<Usuario> findById(UUID id){return usuarioRepo.findById(id);}

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepo.findFirstByEmail(email);
    }

    public  Usuario crearUsuario(PostCrearUserDto postCrearUserDto, EnumSet<UserRoles> userRoles){
        if (usuarioRepo.existsByEmailIgnoreCase(postCrearUserDto.email())||administradorRepo.existsByEmailIgnoreCase(postCrearUserDto.email())){
            throw new RuntimeException("El email ya ha sido registrado");
        }
        if (postCrearUserDto.email().isEmpty()){
            throw new RuntimeException("El campo email no puede estar vacio");
        }
        if (postCrearUserDto.name().isEmpty()&&!postCrearUserDto.name().matches("[a-zA-Z]+")){
            throw new RuntimeException("El campo nombre no puede estar vacio");
        }
        if (postCrearUserDto.lastName() != null && (!postCrearUserDto.lastName().matches("[a-zA-Z]+") || postCrearUserDto.lastName().isEmpty())){
            throw new RuntimeException("El campo apellidos solo puede contener letras y no puede estar vacio");
        }
        if (!postCrearUserDto.phoneNumber().matches("[0-9]+")){
            throw new RuntimeException("El campo número de teléfono solo puede contener números");
        }
        if (postCrearUserDto.password().isEmpty()){
            throw new RuntimeException("El campo contraseña no puede estar vacio");
        }
        Usuario usuario = Usuario.builder()
                .email(postCrearUserDto.email())
                .name(postCrearUserDto.name())
                .lastName(postCrearUserDto.lastName())
                .pais(postCrearUserDto.pais())
                .provincia(postCrearUserDto.provincia())
                .pueblo(postCrearUserDto.pueblo())
                .password(passwordEncoder.encode(postCrearUserDto.password()))
                .createdAt(LocalDateTime.now())
                .username(postCrearUserDto.name()+postCrearUserDto.lastName())
                .phoneNumber(postCrearUserDto.phoneNumber())
                .birthDate(postCrearUserDto.nacimiento())
                .roles(EnumSet.of(UserRoles.USER))
                .enabled(false)
                .build();

        return usuarioRepo.save(usuario);

    }

    public Usuario createWithRole(PostCrearUserDto postCrearUserDto){
        return crearUsuario(postCrearUserDto,EnumSet.of(UserRoles.USER));
    }
    public GetUsuario getUsuario(UUID uuid){
        GetUsuario usuario = usuarioRepo.getUsuario(uuid);
        return usuario;

    }
    public Usuario setearEnabled(PostLogin postCrearUserDto){
        Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(postCrearUserDto.email());

        if (usuario.isPresent() || usuario.get().isEnabled()){
            usuario.get().setEnabled(true);
            return usuarioRepo.save(usuario.get());
        }else {
            throw new RuntimeException("No se encuentra el usuario");
        }
    }


}
