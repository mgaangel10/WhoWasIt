package com.example.WhoWasIts.FlashPost.controller;

import com.example.WhoWasIts.FlashPost.Dto.VisualizacionDto;
import com.example.WhoWasIts.FlashPost.service.FlashPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor

public class FlashPostController {
    private final FlashPostService flashPostService;

    @GetMapping("usuario/ver/post/una/vez/{id}")
    public ResponseEntity<VisualizacionDto> puedeVer(@PathVariable UUID id){

        VisualizacionDto visualizacion = flashPostService.registrarVisualizacion(id);
        return ResponseEntity.ok(visualizacion);
    }
}
