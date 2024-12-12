package com.example.WhoWasIts.Postear.controller;

import com.example.WhoWasIts.Postear.Dto.CrearPostDto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Dto.VisualizacionDto;
import com.example.WhoWasIts.Postear.model.Visualizacion;
import com.example.WhoWasIts.Postear.service.PostearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostearController {
    private final PostearService postearService;


    @PostMapping("usuario/nuevo/post")
    public ResponseEntity<PostDto> nuevoPost(@RequestBody CrearPostDto crearPostDto  ){
        PostDto postDto = postearService.crearPost(crearPostDto);

        return ResponseEntity.status(201).body(postDto);
    }

    @GetMapping("usuario/ver/post")
    public ResponseEntity<List<PostDto>> verPost(){
        List<PostDto> postDtos = postearService.verPost();

        return ResponseEntity.ok(postDtos);
    }

    @PostMapping("usuario/recomendar/{id}")
    public ResponseEntity<PostDto> recomendar(@PathVariable UUID id){
        PostDto postDto = postearService.recomendar(id);
        return ResponseEntity.status(201).body(postDto);
    }

    @GetMapping("usuario/ver/detalles/post/{id}")
    public ResponseEntity<PostDto> detallesPost(@PathVariable UUID id){
        PostDto postDto = postearService.findByID(id);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping("usuario/ver/post/una/vez/{id}")
    public ResponseEntity<VisualizacionDto> puedeVer(@PathVariable UUID id){

        VisualizacionDto visualizacion = postearService.registrarVisualizacion(id);
        return ResponseEntity.ok(visualizacion);
    }

}
