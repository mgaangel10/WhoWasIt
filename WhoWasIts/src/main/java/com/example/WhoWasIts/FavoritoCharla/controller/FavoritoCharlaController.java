package com.example.WhoWasIts.FavoritoCharla.controller;

import com.example.WhoWasIts.Favorito.Dto.FavoritoDto;
import com.example.WhoWasIts.Favorito.service.FavoritoService;
import com.example.WhoWasIts.FavoritoCharla.Dto.FavoritoCharlaDto;
import com.example.WhoWasIts.FavoritoCharla.model.FavoritoIdCharla;
import com.example.WhoWasIts.FavoritoCharla.service.FavoritoCharlaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FavoritoCharlaController {

    private final FavoritoCharlaService favoritoCharlaService;

    @PostMapping("usuario/dar/megusta/charla/{id}")
    public ResponseEntity<FavoritoCharlaDto> darMegusta(@PathVariable UUID id){
        FavoritoCharlaDto postDto = favoritoCharlaService.darAFavorito(id);
        return ResponseEntity.status(201).body(postDto);
    }
}
