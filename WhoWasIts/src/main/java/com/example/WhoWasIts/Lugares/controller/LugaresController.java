package com.example.WhoWasIts.Lugares.controller;

import com.example.WhoWasIts.Lugares.Dto.LugarBuscarDto;
import com.example.WhoWasIts.Lugares.Dto.LugaresAndPueblosDto;
import com.example.WhoWasIts.Lugares.Dto.LugaresDto;
import com.example.WhoWasIts.Lugares.service.LugaresService;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.service.PostearService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LugaresController {

    private final LugaresService lugaresService;



    @GetMapping("usuario/todos/lugares")
    public ResponseEntity<LugaresAndPueblosDto> todos(){
        LugaresAndPueblosDto lugaresAndPueblosDto = lugaresService.todos();
        return ResponseEntity.ok(lugaresAndPueblosDto);
    }
}
