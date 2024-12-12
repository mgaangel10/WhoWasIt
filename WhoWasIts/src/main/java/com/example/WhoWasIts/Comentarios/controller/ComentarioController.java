package com.example.WhoWasIts.Comentarios.controller;

import com.example.WhoWasIts.Comentarios.Dto.ComentarDto;
import com.example.WhoWasIts.Comentarios.Dto.ComentariosDto;
import com.example.WhoWasIts.Comentarios.service.ComentarioService;
import jakarta.persistence.GeneratedValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ComentarioController {
    private final ComentarioService comentarioService;

    @PostMapping("usuario/comentar/{id}")
    public ResponseEntity<ComentariosDto> comentar(@PathVariable UUID id, @RequestBody ComentarDto comentarDto){
        ComentariosDto comentariosDto = comentarioService.crearComentario(id, comentarDto);
        return ResponseEntity.status(201).body(comentariosDto);
    }

    @PostMapping("usuario/comentar/charla/{id}")
    public ResponseEntity<ComentariosDto> comentarCharla(@PathVariable UUID id, @RequestBody ComentarDto comentarDto){
        ComentariosDto comentariosDto = comentarioService.crearComentarioCharla(id, comentarDto);
        return ResponseEntity.status(201).body(comentariosDto);
    }

    @GetMapping("usuario/ver/comentarios/{id}")
    public ResponseEntity<List<ComentariosDto>> verComentarios(@PathVariable UUID id){
        List<ComentariosDto> comentariosDtos = comentarioService.verComentarios(id);
        return ResponseEntity.ok(comentariosDtos);
    }
    @GetMapping("usuario/ver/comentarios/charla/{id}")
    public ResponseEntity<List<ComentariosDto>> verComentariosCharla(@PathVariable UUID id){
        List<ComentariosDto> comentariosDtos = comentarioService.verComentariosCharlas(id);
        return ResponseEntity.ok(comentariosDtos);
    }
}
