package com.example.WhoWasIts.Postear.service;

import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;
import com.example.WhoWasIts.Comentarios.model.Comentario;
import com.example.WhoWasIts.Cuestionario.Repositorio.CuestionarioRepo;
import com.example.WhoWasIts.Cuestionario.model.Cuestionario;
import com.example.WhoWasIts.Postear.Dto.CrearPostDto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostearService {

    private final PostearRepo postearRepo;
    private final UsuarioAnonimoRepo usuarioAnonimoRepo;
    private final UsuarioRepo usuarioRepo;
    private final CuestionarioRepo cuestionarioRepo;


    public String obtenerMencionesComoString(String contenido) {
        StringBuilder menciones = new StringBuilder();
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(contenido);
        while (matcher.find()) {
            menciones.append(matcher.group()).append(",");
        }
        return menciones.toString().trim();
    }
    public PostDto crearPost(CrearPostDto crearPostDto) {
        // Obtiene el usuario autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            Optional<Usuario> usuarioOptional = usuarioRepo.findByEmailIgnoreCase(email);

            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                UsuarioAnonimo usuarioAnonimo = usuario.getUsuarioAnonimo();
                // Verifica si se trata de un post o un repost
                Postear postear = new Postear();
                postear.setContenido(crearPostDto.contenido());
                postear.setUsuarioAnonimo(usuarioAnonimo);
                postear.setFechaHora(LocalDateTime.now());
                postear.setMenciones(obtenerMencionesComoString(crearPostDto.contenido()));

                if (crearPostDto.id() != null) {
                    // Caso repost: busca el post original
                    Optional<Postear> postOriginalOptional = postearRepo.findById(crearPostDto.id());
                    if (postOriginalOptional.isPresent()) {
                        postear.setPostears(postOriginalOptional.get());
                        postear.getReposts().add(postOriginalOptional.get());
                    } else {
                        throw new IllegalArgumentException("El post original con el ID proporcionado no existe.");
                    }
                }

                if (crearPostDto.idCuestionario()!=null){
                    Optional<Cuestionario> cuestionario = cuestionarioRepo.findById(crearPostDto.idCuestionario());
                    if (cuestionario.isPresent()){
                        postear.setCuestionario(cuestionario.get());
                    }else {
                        postear.setCuestionario(null);
                    }
                }else {
                    postear.setCuestionario(null);
                }

                // Guarda el post/repost y devuelve el DTO
                postearRepo.save(postear);
                return PostDto.of(postear);
            } else {
                throw new IllegalStateException("Usuario no encontrado en el contexto de autenticación.");
            }
        } else {
            throw new IllegalStateException("Principal no es una instancia de UserDetails.");
        }
    }

    public List<PostDto> verPost(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){
              List<Postear> postears = postearRepo.findAll();
              List<PostDto> postDtos = postears.stream().map(PostDto::of).collect(Collectors.toList());

              return postDtos;
            }
        }
        return null;
    }

    public PostDto recomendar(UUID id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear1 = postearRepo.findById(id);
            if (usuario.isPresent()){
                Postear postear = new Postear();
                postear.setRecomendar(true);
                postear.setPostears(postear1.get());
                postear.setUsuarioAnonimo(usuario.get().getUsuarioAnonimo());
                postear.setFechaHora(LocalDateTime.now());
                postearRepo.save(postear);
                return PostDto.of(postear);
            }
        }
        return null;
    }

    public PostDto findByID(UUID id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear1 = postearRepo.findById(id);
            if (usuario.isPresent()){
               return PostDto.of(postear1.get());
            }
        }
        return null;
    }




}
