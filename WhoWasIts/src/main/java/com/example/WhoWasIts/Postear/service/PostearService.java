package com.example.WhoWasIts.Postear.service;

import com.example.WhoWasIts.Postear.Dto.CrearPostDto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Dto.RerePostDto;
import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.UsuarioAnonimo.Dto.UsuarioAnonimoDto;
import com.example.WhoWasIts.UsuarioAnonimo.model.Avatar;
import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import com.example.WhoWasIts.UsuarioAnonimo.repositorio.UsuarioAnonimoRepo;
import com.example.WhoWasIts.UsuarioAnonimo.service.UsuarioAnonimoService;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PostearService {

    private final PostearRepo postearRepo;
    private final UsuarioAnonimoRepo usuarioAnonimoRepo;
    private final UsuarioRepo usuarioRepo;


    public String obtenerMencionesComoString(String contenido) {
        StringBuilder menciones = new StringBuilder();
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(contenido);
        while (matcher.find()) {
            menciones.append(matcher.group()).append(",");
        }
        return menciones.toString().trim();
    }
    public PostDto crearPost(CrearPostDto crearPostDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){

                String menciones = obtenerMencionesComoString(crearPostDto.contenido());


                Postear postear = new Postear();
                postear.setContenido(crearPostDto.contenido());
                postear.setUsuarioAnonimo(usuario.get().getUsuarioAnonimo());
                postear.setFechaHora(LocalDateTime.now());
                postear.setMenciones(menciones);
                postearRepo.save(postear);
                return PostDto.of(postear);

            }
        }

        return null;
    }

    public RerePostDto repostearUnPost(UUID idPost,CrearPostDto crearPostDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear1 = postearRepo.findById(idPost);
            if (usuario.isPresent()){

                String menciones = obtenerMencionesComoString(crearPostDto.contenido());


                Postear postear = new Postear();
                postear.setContenido(crearPostDto.contenido());
                postear.setUsuarioAnonimo(usuario.get().getUsuarioAnonimo());
                postear.setFechaHora(LocalDateTime.now());
                postear.setMenciones(menciones);
                postear.setPostears(postear1.get());
                postearRepo.save(postear);

                return RerePostDto.of(postear);

            }
        }

        return null;
    }


}
