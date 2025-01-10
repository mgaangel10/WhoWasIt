package com.example.WhoWasIts.Postear.Dto;

import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;
import com.example.WhoWasIts.Cuestionario.Dto.CuestionarioDto;
import com.example.WhoWasIts.FlashPost.Dto.VisualizacionDto;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.Postear.model.Pueblos;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PostDto(UUID id,
                      String contenido,
                      String palabraDesordenada,
                      String nombreUsuario,
                      String foto,
                      String tiempoPublicado,
                      String meciones,
                      int numeroMegustas,
                      List<ComentariosDto> comentariosDtos,
                      PostDto ps,
                      int numeroDeRepost,
                      int numeroRecomendar,
                      boolean recomendar,
                      CuestionarioDto cuestionarioDto,
                      boolean soloUnaVez,
                      boolean desorden,
                      List<VisualizacionDto> visualizacionDtos,
                      Pueblos pueblos


                      ) {

    public static PostDto of (Postear p){

        return new PostDto(
                p.getId(),
                p.getContenido(),
                p.getPalabraDesordenada(),
                p.getUsuarioAnonimo().getNombreDelUsuario(),
                p.getUsuarioAnonimo().getAvatar().getImagen(),
                p.getTiempoPublicado(),
                p.getMenciones(),
                p.getFavoritoList()==null ? 0 : p.getFavoritoList().size(),
                p.getComentarios()== null ? null : p.getComentarios().stream().map(ComentariosDto::of).collect(Collectors.toList()),
                p.getPostears() == null ? null:PostDto.of(p.getPostears()), // Manejo de repost,
                p.getReposts()== null ? 0 : p.getReposts().size(),
                p.getRecomendars()== null ? 0 : p.getRecomendars().size(),
                p.isRecomendar(),
                p.getCuestionario() != null ? CuestionarioDto.of(p.getCuestionario()) : null,
                p.isPostUnaSolaVez(),
                p.isPalabrasDesordenadas(),
                p.getVisualizacions() == null ? null : p.getVisualizacions().stream().map(VisualizacionDto::of).collect(Collectors.toList()),
                p.getPueblos()
                // Verifica si el cuestionario es null
        );
    }
}
