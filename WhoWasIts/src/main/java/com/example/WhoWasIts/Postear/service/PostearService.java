package com.example.WhoWasIts.Postear.service;

import com.example.WhoWasIts.Cuestionario.Repositorio.CuestionarioRepo;
import com.example.WhoWasIts.Cuestionario.model.Cuestionario;
import com.example.WhoWasIts.FlashPost.model.Visualizacion;
import com.example.WhoWasIts.Postear.Dto.CrearPostDto;
import com.example.WhoWasIts.Postear.Dto.EstadisticasPostDto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import com.example.WhoWasIts.FlashPost.repositorio.VisualizacionRepo;
import com.example.WhoWasIts.Postear.Repositorio.PueblosRepo;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.Postear.model.Pueblos;
import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import com.example.WhoWasIts.UsuarioAnonimo.repositorio.UsuarioAnonimoRepo;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
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
    private final VisualizacionRepo visualizacionRepo;
    private final PueblosRepo pueblosRepo;


    public String obtenerMencionesComoString(String contenido) {
        StringBuilder menciones = new StringBuilder();
        Pattern pattern = Pattern.compile("@\\w+");
        Matcher matcher = pattern.matcher(contenido);
        while (matcher.find()) {
            menciones.append(matcher.group()).append(",");
        }
        return menciones.toString().trim();
    }
    public PostDto crearPost(CrearPostDto crearPostDto,UUID idPueblo) {
        // Obtiene el usuario autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            String email = userDetails.getUsername();
            Optional<Usuario> usuarioOptional = usuarioRepo.findByEmailIgnoreCase(email);
            Optional<Pueblos> pueblos = pueblosRepo.findById(idPueblo);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                UsuarioAnonimo usuarioAnonimo = usuario.getUsuarioAnonimo();

                // Verifica si se trata de un post o un repost
                Postear postear = new Postear();
                postear.setContenido(crearPostDto.contenido());
                postear.setUsuarioAnonimo(usuarioAnonimo);
                postear.setPueblos(pueblos.get());
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

                if (!crearPostDto.postUnaVez()){
                    postear.setPostUnaSolaVez(false);
                }else {
                    postear.setPostUnaSolaVez(true);
                }

                if (crearPostDto.desorden()){
                    postear.setPalabrasDesordenadas(true);
                    String conten = desordenarContenido(crearPostDto.contenido());
                    postear.setPalabraDesordenada(conten);
                }else {
                    postear.setPalabrasDesordenadas(false);
                }



                // Guarda el post/repost y devuelve el DTO
                postearRepo.save(postear);
                String tiempoPublicado = calcularTiempoPublicado(postear.getFechaHora());
                postear.setTiempoPublicado(tiempoPublicado);
                postearRepo.save(postear);
                return PostDto.of(postear);
            } else {
                throw new IllegalStateException("Usuario no encontrado en el contexto de autenticación.");
            }
        } else {
            throw new IllegalStateException("Principal no es una instancia de UserDetails.");
        }
    }
    public String desordenarContenido(String input) {
        // Convertir la cadena en una lista de caracteres
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        // Desordenar la lista
        Collections.shuffle(characters);

        // Reconstruir la cadena desordenada
        StringBuilder resultado = new StringBuilder();
        for (char c : characters) {
            resultado.append(c);
        }

        return resultado.toString();
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


    public List<PostDto> verPost() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre = ((UserDetails) principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()) {
                List<Postear> postears = postearRepo.findAll();
                List<Visualizacion> visualizacions = visualizacionRepo.findAll();

                // Filtrar las visualizaciones del usuario actual
                List<Visualizacion> visualizacionsUsuario = visualizacions.stream()
                        .filter(visualizacion -> visualizacion.getUsuario().getId().equals(usuario.get().getId()))
                        .collect(Collectors.toList());

                // Verificar si no hay visualizaciones registradas o si todas están expiradas
                boolean todasVisualizacionesExpiradas = visualizacionsUsuario.stream().allMatch(visualizacion ->
                        visualizacion.getFechaVisualizacion().plusMinutes(1).isBefore(LocalDateTime.now())
                );

                if (visualizacionsUsuario.isEmpty() || todasVisualizacionesExpiradas) {
                    // Si no hay visualizaciones o todas están expiradas, limpiar visualizaciones de cada post
                    postears.forEach(postear -> postear.setVisualizacions(new ArrayList<>()));
                }

                // Actualizar tiempo publicado para cada post
                List<Postear> postearsActualizados = postears.stream().map(postear -> {
                    String tiempo = calcularTiempoPublicado(postear.getFechaHora());
                    postear.setTiempoPublicado(tiempo);
                    return postearRepo.save(postear);
                }).collect(Collectors.toList());

                // Convertir los posts a DTOs y retornarlos
                return postearsActualizados.stream().map(PostDto::of).collect(Collectors.toList());
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

    public EstadisticasPostDto estadisticasPostDto (UUID idPost){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear1 = postearRepo.findById(idPost);
            if (usuario.isPresent()){
                if (postear1.get().getUsuarioAnonimo().getUsuario().getId().equals(usuario.get().getId())){
                    return EstadisticasPostDto.of(postear1.get());
                }
            }
        }
        return null;
    }











}
