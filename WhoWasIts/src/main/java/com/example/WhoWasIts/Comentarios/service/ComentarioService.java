package com.example.WhoWasIts.Comentarios.service;

import com.example.WhoWasIts.Charlas.model.Charlas;
import com.example.WhoWasIts.Charlas.repositorio.CharlaRepo;
import com.example.WhoWasIts.Comentarios.Dto.ComentarDto;
import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;
import com.example.WhoWasIts.Comentarios.Repositorio.ComentarioRepo;
import com.example.WhoWasIts.Comentarios.model.Comentario;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepo comentarioRepo;
    private final UsuarioRepo usuarioRepo;
    private final PostearRepo postearRepo;
    private final CharlaRepo charlaRepo;

    public ComentariosDto crearComentario(UUID idPost,ComentarDto comentarDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear = postearRepo.findById(idPost);
            if (usuario.isPresent()){
                    Comentario comentario = new Comentario();
                    comentario.setContenido(comentarDto.contenido());
                    comentario.setPostear(postear.get());
                    comentario.setUsuario(usuario.get());
                    comentarioRepo.save(comentario);
                return ComentariosDto.of(comentario);
            }
        }
        return null;
    }

    public ComentariosDto crearComentarioCharla(UUID idPost,ComentarDto comentarDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Charlas> charlas = charlaRepo.findById(idPost);
            if (usuario.isPresent()){
                Comentario comentario = new Comentario();
                comentario.setContenido(comentarDto.contenido());
                comentario.setCharla(charlas.get());
                comentario.setUsuario(usuario.get());
                comentarioRepo.save(comentario);
                return ComentariosDto.of(comentario);
            }
        }
        return null;
    }


    public List<ComentariosDto> verComentarios(UUID idPost){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear = postearRepo.findById(idPost);
            if (usuario.isPresent()){
                List<Comentario> comentarios = comentarioRepo.findByPostear(postear.get());
                List<ComentariosDto> comentariosDtos = comentarios.stream().map(ComentariosDto::of).collect(Collectors.toList());
                return comentariosDtos;
            }
        }
        return null;
    }

    public List<ComentariosDto> verComentariosCharlas(UUID charla){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Charlas> charlas = charlaRepo.findById(charla);
            if (usuario.isPresent()){
                List<Comentario> comentarios = comentarioRepo.findByCharla(charlas.get());
                List<ComentariosDto> comentariosDtos = comentarios.stream().map(ComentariosDto::of).collect(Collectors.toList());
                return comentariosDtos;
            }
        }
        return null;
    }
}
