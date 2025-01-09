package com.example.WhoWasIts.UsuarioAnonimo.service;

import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import com.example.WhoWasIts.Postear.model.Postear;
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

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioAnonimoService {

    private final UsuarioAnonimoRepo usuarioAnonimoRepo;
    private final AvatarRepo avatarRepo;
    private final UsuarioRepo usuarioRepo;
    private final PostearRepo postearRepo;

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
                usuarioAnonimo.setConocidoComo(obtenerMencionesComoString(crearUsuarioAnoninoDto.conocidoComo()));
                usuarioAnonimoRepo.save(usuarioAnonimo);
                usuario.get().setUsuarioAnonimo(usuarioAnonimo);
                usuarioRepo.save(usuario.get());
                return UsuarioAnonimoDto.of(usuarioAnonimo);
            }
        }

        return null;
    }

    public UsuarioAnonimoDto verPerfil(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){
                return UsuarioAnonimoDto.of(usuario.get().getUsuarioAnonimo());
            }
        }

        return null;
    }
    public String obtenerMencionesComoString(String contenido) {
        StringBuilder menciones = new StringBuilder();
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(contenido);
        while (matcher.find()) {
            menciones.append(matcher.group()).append(",");
        }
        return menciones.toString().trim();
    }

    public List<PostDto> verMenciones(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){
                List<Postear> postears = postearRepo.findAll();
                List<Postear> postears1 = postears.stream().filter(postear -> postear.getMenciones().contains(usuario.get().getUsuarioAnonimo().getConocidoComo())).collect(Collectors.toList());
                List<PostDto> postDtos = postears1.stream().map(PostDto::of).collect(Collectors.toList());
                return postDtos;
            }
        }

        return null;
    }

    public List<Avatar> todosLOsAvatares(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){
                List<Avatar> avatars = avatarRepo.findAll();
                return avatars;
            }
        }

        return null;
    }

}
