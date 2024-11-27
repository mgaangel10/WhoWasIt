package com.example.WhoWasIts.Favorito.controller;

import com.example.WhoWasIts.Favorito.service.FavoritoService;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService favoritoService;

    @PostMapping("usuario/dar/megusta/{id}")
    public ResponseEntity<PostDto> darMegusta(@PathVariable UUID id){
        PostDto postDto = favoritoService.darAFavorito(id);
        return ResponseEntity.status(201).body(postDto);
    }
}
