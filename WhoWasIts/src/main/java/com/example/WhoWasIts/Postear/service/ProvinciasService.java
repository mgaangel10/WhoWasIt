package com.example.WhoWasIts.Postear.service;

import com.example.WhoWasIts.Lugares.model.Lugares;
import com.example.WhoWasIts.Lugares.repositorio.LugaresRepo;
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

import java.time.Duration;
import java.time.LocalDateTime;
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
    private final LugaresRepo lugaresRepo;

    public String calcularTiempoPublicado(LocalDateTime fechaHora) {
        LocalDateTime ahora = LocalDateTime.now();
        Duration duracion = Duration.between(fechaHora, ahora);

        long segundos = duracion.getSeconds();
        long minutos = duracion.toMinutes();
        long horas = duracion.toHours();
        long dias = duracion.toDays();

        if (segundos < 60) {
            return  segundos + (segundos == 1 ? " segundo" : " segundos");
        } else if (minutos < 60) {
            return minutos + (minutos == 1 ? " minuto" : " minutos");
        } else if (horas < 24) {
            return  horas + (horas == 1 ? " hora" : " horas");
        } else {
            return + dias + (dias == 1 ? " día" : " días");
        }
    }

    public List<PostDto> filtrarPorPueblos(UUID id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Lugares> lugares = lugaresRepo.findById(id);
            Optional<Pueblos> pueblos = pueblosRepo.findById(id);
            if (usuario.isPresent()&&pueblos.isPresent()&&lugares.isEmpty()){
                List<Postear> postears = postearRepo.findAll();
                List<Postear> postears1 = postears.stream().filter(postear -> postear.getPueblos().getId().equals(id)).collect(Collectors.toList());
                List<Postear> postears2 = postears1.stream().map(postear -> {
                    String tiempo = calcularTiempoPublicado(postear.getFechaHora());
                    postear.setTiempoPublicado(tiempo);
                    return postearRepo.save(postear);
                }).collect(Collectors.toList());
                List<PostDto> postDtos = postears2.stream().map(PostDto::of).collect(Collectors.toList());
                return postDtos;

            }else {
                List<Postear> postears = postearRepo.findAll();
                List<Postear> postears1 = postears.stream().filter(postear -> postear.getLugares().getId().equals(id)).collect(Collectors.toList());
                List<Postear> postears2 = postears1.stream().map(postear -> {
                    String tiempo = calcularTiempoPublicado(postear.getFechaHora());
                    postear.setTiempoPublicado(tiempo);
                    return postearRepo.save(postear);
                }).collect(Collectors.toList());
                List<PostDto> postDtos = postears2.stream().map(PostDto::of).collect(Collectors.toList());
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
