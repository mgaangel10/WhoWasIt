package com.example.WhoWasIts.Cuestionario.service;

import com.example.WhoWasIts.Cuestionario.Dto.*;
import com.example.WhoWasIts.Cuestionario.Repositorio.CuestionarioRepo;
import com.example.WhoWasIts.Cuestionario.Repositorio.OpcionesRepo;
import com.example.WhoWasIts.Cuestionario.Repositorio.VotoRepo;
import com.example.WhoWasIts.Cuestionario.model.Cuestionario;
import com.example.WhoWasIts.Cuestionario.model.Opciones;
import com.example.WhoWasIts.Cuestionario.model.Voto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import jakarta.transaction.Transactional;
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
public class CuestionarioService {

    private final CuestionarioRepo cuestionarioRepo;
    private final OpcionesRepo opcionesRepo;
    private final VotoRepo votoRepo;
    private final UsuarioRepo usuarioRepo;

    public CuestionarioDto crearCuestionario(CrearCuestionario cuestionario){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()){
                Cuestionario cuestionario1 = new Cuestionario();
                cuestionario1.setTitulo(cuestionario.titulo());
                cuestionarioRepo.save(cuestionario1);
                return CuestionarioDto.of(cuestionario1);
            }
        }
        return null;
    }

    public OpcionesDto addOpciones(UUID id, CrearOpcionesDto crearOpcionesDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Cuestionario> cuestionario = cuestionarioRepo.findById(id);
            if (usuario.isPresent()){
                Opciones opciones = new Opciones();
                opciones.setOpcion(crearOpcionesDto.opciones());
                opciones.setCuestionario(cuestionario.get());
                opciones.setNumeroVotos(0);
                opcionesRepo.save(opciones);
                cuestionario.get().getOpciones().add(opciones);
                cuestionarioRepo.save(cuestionario.get());
                return OpcionesDto.of(opciones);

            }
        }
        return null;
    }
    public void eliminarOpciones(UUID id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre = ((UserDetails) principal).getUsername();

            // Verificar que el usuario exista en la base de datos
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);

            if (usuario.isPresent()) {
                // Buscar la opción que quieres eliminar
                Optional<Opciones> opciones = opcionesRepo.findById(id);

                if (opciones.isPresent()) {
                    Opciones opcion = opciones.get();

                    // Eliminar la referencia en el cuestionario antes de eliminar la opción
                    for (Cuestionario cuestionario : cuestionarioRepo.findAll()) {
                        cuestionario.getOpciones().remove(opcion);
                        cuestionarioRepo.save(cuestionario);  // Guarda el cuestionario actualizado
                    }

                    // Ahora puedes eliminar la opción de la base de datos
                    opcionesRepo.delete(opcion);  // Eliminar la opción
                } else {
                    throw new IllegalArgumentException("La opción con id " + id + " no existe.");
                }
            } else {
                throw new IllegalArgumentException("El usuario no existe.");
            }
        }
    }


    public VotarDto votar(UUID id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Opciones> opciones = opcionesRepo.findById(id);
            if (usuario.isPresent()){

                Opciones opcion = opcionesRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Opción no encontrada"));

                // Verificar si el usuario ya votó en este cuestionario
                boolean yaVoto = opcion.getCuestionario().getOpciones().stream()
                        .flatMap(opt -> opt.getVotos().stream())
                        .anyMatch(voto -> voto.getUsuario().getId().equals(usuario.get().getId()));

                if (yaVoto) {
                    throw new RuntimeException("El usuario ya ha votado en este cuestionario");
                }

                // Incrementar el número de votos y registrar el voto
                opcion.setNumeroVotos(opcion.getNumeroVotos() + 1);

                Voto voto = Voto.builder()
                        .usuario(usuario.get())
                        .opciones(opcion)
                        .build();

                votoRepo.save(voto);

                // Asociar el voto a la opción y guardar cambios
                opcion.getVotos().add(voto);
                opcionesRepo.save(opcion);
                resultadoVotaciones(opcion.getCuestionario().getId());

                return VotarDto.of(voto);

            }
        }
        return null;
    }

    public CuestionarioDto resultadoVotaciones(UUID id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre = ((UserDetails) principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Cuestionario> cuestionario = cuestionarioRepo.findById(id);

            if (usuario.isPresent() && cuestionario.isPresent()) {
                // Obtener todas las opciones del cuestionario
                List<Opciones> opciones = cuestionario.get().getOpciones();

                // Calcular el total de votos sumando los tamaños de las listas `votos` de todas las opciones
                int numeroTotalDeVotos = opciones.stream()
                        .mapToInt(opcion -> opcion.getVotos().size())
                        .sum();

                if (numeroTotalDeVotos > 0) {
                    // Calcular el porcentaje de votos para cada opción
                    opciones.forEach(opcion -> {
                        int votosDeLaOpcion = opcion.getVotos().size();
                        int porcentaje = (votosDeLaOpcion * 100) / numeroTotalDeVotos;

                        // Actualizar campos de la opción
                        opcion.setNumeroVotos(votosDeLaOpcion);
                        opcion.setPorcentajeVotos(porcentaje);

                        // Guardar la opción actualizada
                        opcionesRepo.save(opcion);
                    });
                } else {
                    // Si no hay votos, establecer `numeroVotos` y `porcentajeVotos` en 0
                    opciones.forEach(opcion -> {
                        opcion.setNumeroVotos(0);
                        opcion.setPorcentajeVotos(0);
                        opcionesRepo.save(opcion);
                    });
                }

                // Actualizar el total de votos en el cuestionario
                cuestionario.get().setTotalDeVotos(numeroTotalDeVotos);
                cuestionarioRepo.save(cuestionario.get());

                // Devolver el DTO del cuestionario actualizado
                return CuestionarioDto.of(cuestionario.get());
            }
        }
        return null;
    }

    public List<CuestionarioDto> todosLosResultadosCuestionario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);

            if (usuario.isPresent()){
               List<Voto> votos = votoRepo.findAll();
               List<Voto> votos1 = votos.stream().filter(voto -> voto.getUsuario().getId().equals(usuario.get().getId())).collect(Collectors.toList());
               List<Cuestionario> cuestionarios = votos1.stream().map(voto -> voto.getOpciones().getCuestionario()).collect(Collectors.toList());
               List<CuestionarioDto> cuestionarioDtos = cuestionarios.stream().map(CuestionarioDto::of).collect(Collectors.toList());
               return cuestionarioDtos;

            }
        }
        return null;
    }

    public List<OpcionesDto> verOpciones(UUID id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Cuestionario> cuestionario = cuestionarioRepo.findById(id);
            if (usuario.isPresent()){
                List<Opciones> opciones = opcionesRepo.findByCuestionarioId(id);
                List<OpcionesDto> opcionesDtos = opciones.stream().map(OpcionesDto::of).collect(Collectors.toList());
                return opcionesDtos;
            }
        }
        return null;
    }

    public void elimianrCuestionario(UUID id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Cuestionario> cuestionario = cuestionarioRepo.findById(id);
            if (usuario.isPresent()){
                cuestionarioRepo.delete(cuestionario.get());
            }
        }

    }
}
