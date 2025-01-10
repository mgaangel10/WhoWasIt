package com.example.WhoWasIts.Postear.service;

import com.example.WhoWasIts.Postear.Dto.EstadisticasPostDto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Dto.ProvinciasDto;
import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import com.example.WhoWasIts.Postear.Repositorio.ProvinciasRepo;
import com.example.WhoWasIts.Postear.Repositorio.PueblosRepo;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.Postear.model.Provincias;
import com.example.WhoWasIts.Postear.model.Pueblos;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProvinciasService {
    private final UsuarioRepo usuarioRepo;
    private final PueblosRepo pueblosRepo;
    private final PostearRepo postearRepo;
    private final ProvinciasRepo provinciasRepo;


    public List<PostDto> filtrarPorPueblos(UUID id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Pueblos> pueblos = pueblosRepo.findById(id);
            if (usuario.isPresent()){
                List<Postear> postears = postearRepo.findAll();
                List<Postear> postears1 = postears.stream().filter(postear -> postear.getPueblos().getId().equals(id)).collect(Collectors.toList());
                List<PostDto> postDtos = postears1.stream().map(PostDto::of).collect(Collectors.toList());
                return postDtos;

            }
        }
        return null;
    }

    public List<ProvinciasDto> todosLosPueblosprovincias(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);

            if (usuario.isPresent()){
                List<Provincias> provincias = provinciasRepo.findAll();
                List<ProvinciasDto> provinciasDtos = provincias.stream().map(ProvinciasDto::of).collect(Collectors.toList());
                return provinciasDtos;
            }
        }
        return null;
    }
}
