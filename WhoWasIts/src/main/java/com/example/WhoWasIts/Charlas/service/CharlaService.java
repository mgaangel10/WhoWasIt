package com.example.WhoWasIts.Charlas.service;

import com.example.WhoWasIts.Charlas.Dto.CharlasDto;
import com.example.WhoWasIts.Charlas.Dto.CrearCharlaDto;
import com.example.WhoWasIts.Charlas.model.Charlas;
import com.example.WhoWasIts.Charlas.repositorio.CharlaRepo;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharlaService {
    private final UsuarioRepo usuarioRepo;
    private final CharlaRepo charlaRepo;
    private static final LocalTime INICIO_CHARLAS_NOCTURNAS = LocalTime.of(22, 0); // 22:00
    private static final LocalTime FIN_CHARLAS_NOCTURNAS = LocalTime.of(6, 0);    // 06:00

    public String obtenerMencionesComoString(String contenido) {
        StringBuilder menciones = new StringBuilder();
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(contenido);
        while (matcher.find()) {
            menciones.append(matcher.group()).append(",");
        }
        return menciones.toString().trim();
    }
    private boolean esHoraNocturna(LocalTime horaActual) {
        // Verificar si el rango abarca la medianoche
        if (INICIO_CHARLAS_NOCTURNAS.isAfter(FIN_CHARLAS_NOCTURNAS)) {
            return horaActual.isAfter(INICIO_CHARLAS_NOCTURNAS) || horaActual.isBefore(FIN_CHARLAS_NOCTURNAS);
        } else {
            return horaActual.isAfter(INICIO_CHARLAS_NOCTURNAS) && horaActual.isBefore(FIN_CHARLAS_NOCTURNAS);
        }
    }
    public CharlasDto crearCharlas(CrearCharlaDto crearCharlaDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){
                LocalTime horaActual = LocalTime.now();
                if (!esHoraNocturna(horaActual)) {
                    Charlas charlas = new Charlas();
                    charlas.setContenido(crearCharlaDto.contenido());
                    charlas.setMenciones(obtenerMencionesComoString(crearCharlaDto.contenido()));
                    charlas.setFechaHora(LocalDateTime.now());
                    charlas.setUsuarioAnonimo(usuario.get().getUsuarioAnonimo());
                    charlaRepo.save(charlas);
                    return CharlasDto.of(charlas);
                } else {
                    throw new IllegalStateException("Las charlas solo pueden ser creadas entre las 22:00 y las 6:00.");
                }
            }
        }
        return null;
    }
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
    public List<CharlasDto> verCharlas (){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){
                List<Charlas> charlas = charlaRepo.findAll();
                List<Charlas> charlas1 = charlas.stream().map(charlas2 -> {
                    String actual = calcularTiempoPublicado(charlas2.getFechaHora());
                    charlas2.setTiempoPublicado(actual);
                    charlaRepo.save(charlas2);
                    return charlas2;
                }).collect(Collectors.toList());
                List<CharlasDto> charlasDtos = charlas1.stream().map(CharlasDto::of).collect(Collectors.toList());
                return charlasDtos;
            }
        }
        return null;
    }
 }
