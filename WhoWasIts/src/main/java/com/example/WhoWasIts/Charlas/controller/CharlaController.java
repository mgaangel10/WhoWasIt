package com.example.WhoWasIts.Charlas.controller;

import com.example.WhoWasIts.Charlas.Dto.CharlasDto;
import com.example.WhoWasIts.Charlas.Dto.CrearCharlaDto;
import com.example.WhoWasIts.Charlas.service.CharlaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CharlaController {

    private final CharlaService charlaService;

    @PostMapping("usuario/crear/charla")
    public ResponseEntity<CharlasDto> crearCharla(@RequestBody CrearCharlaDto crearCharlaDto){
        CharlasDto charlasDto = charlaService.crearCharlas(crearCharlaDto);
        return ResponseEntity.status(201).body(charlasDto);
    }

    @GetMapping("usuario/ver/charlas")
    public ResponseEntity<List<CharlasDto>> verCharlas(){
        List<CharlasDto>charlasDtos = charlaService.verCharlas();
        return ResponseEntity.ok(charlasDtos);
    }
}
