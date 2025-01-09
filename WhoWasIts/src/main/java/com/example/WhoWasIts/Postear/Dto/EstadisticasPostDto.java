package com.example.WhoWasIts.Postear.Dto;

import com.example.WhoWasIts.Comentarios.Dto.DetallesDeComentariosDto;
import com.example.WhoWasIts.Cuestionario.Dto.CuestionarioDto;
import com.example.WhoWasIts.Favorito.Dto.DetallesFavoritosDto;
import com.example.WhoWasIts.Postear.model.Postear;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record EstadisticasPostDto(UUID id,
                                  int numeroMegusta,
                                  int numeroDeCOmentarios,
                                  List<DetallesFavoritosDto> detallesFavoritosDtos,
                                  List<DetallesDeComentariosDto> detallesDeComentariosDtos,
                                  CuestionarioDto cuestionarioDto) {
    public static EstadisticasPostDto of (Postear p){
        return new EstadisticasPostDto(
                p.getId(),
                p.getFavoritoList() == null ? 0 : p.getFavoritoList().size(),
                p.getComentarios() == null ? 0 : p.getComentarios().size(),
                p.getFavoritoList().stream().map(DetallesFavoritosDto::of).collect(Collectors.toList()),
                p.getComentarios().stream().map(DetallesDeComentariosDto::of).collect(Collectors.toList()),
                p.getCuestionario()== null ? null : CuestionarioDto.of(p.getCuestionario())
        );
    }
}
