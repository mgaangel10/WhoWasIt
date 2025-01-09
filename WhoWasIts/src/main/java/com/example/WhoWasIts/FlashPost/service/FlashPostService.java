package com.example.WhoWasIts.FlashPost.service;

import com.example.WhoWasIts.FlashPost.Dto.UsarioVeFlashPostDto;
import com.example.WhoWasIts.FlashPost.model.Visualizacion;
import com.example.WhoWasIts.FlashPost.repositorio.VisualizacionRepo;
import com.example.WhoWasIts.FlashPost.Dto.VisualizacionDto;
import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlashPostService {

    private final VisualizacionRepo visualizacionRepo;
    private final UsuarioRepo usuarioRepo;
    private final PostearRepo postearRepo;

    public boolean verPostUnaVez(UUID postId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear = postearRepo.findById(postId);
            if (usuario.isPresent()){
                Optional<Visualizacion> visualizacion = visualizacionRepo.findByUsuarioAndPostear(usuario.get(), postear.get());
                if (visualizacion.isPresent()) {
                    LocalDateTime fechaVisualizacion = visualizacion.get().getFechaVisualizacion();
                    LocalDateTime limite = fechaVisualizacion.plusMinutes(1);
                    return LocalDateTime.now().isBefore(limite);
                }
                return true;
            }
        }
        return  false;
    }

    public VisualizacionDto registrarVisualizacion(UUID postID){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear = postearRepo.findById(postID);
            if (usuario.isPresent()){
                if (verPostUnaVez(postID)) {
                    Visualizacion visualizacion1 = new Visualizacion();
                    visualizacion1.setUsuario(usuario.get());
                    visualizacion1.setPostear(postear.get());
                    visualizacion1.setFechaVisualizacion(LocalDateTime.now());

                    visualizacionRepo.save(visualizacion1);
                    postear.get().getVisualizacions().add(visualizacion1);
                    postearRepo.save(postear.get());
                    UsarioVeFlashPostDto.of(true);
                    return VisualizacionDto.of(visualizacion1);

                }else {

                    return VisualizacionDto.of(null);
                }
            }
        }
        return null;

    }
}
