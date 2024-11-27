package com.example.WhoWasIts.Postear.controller;

import com.example.WhoWasIts.Postear.Dto.CrearPostDto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Dto.RerePostDto;
import com.example.WhoWasIts.Postear.service.PostearService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostearController {
    private final PostearService postearService;


    @PostMapping("usuario/nuevo/post")
    public ResponseEntity<PostDto> nuevoPost(@RequestBody CrearPostDto crearPostDto){
        PostDto postDto = postearService.crearPost(crearPostDto);

        return ResponseEntity.status(201).body(postDto);
    }

    @PostMapping("usuario/rerepostear/{id}")
    public ResponseEntity<RerePostDto> rererPostear(@PathVariable UUID id,@RequestBody CrearPostDto crearPostDto){
        RerePostDto rerePostDto = postearService.repostearUnPost(id, crearPostDto);
        return ResponseEntity.status(201).body(rerePostDto);
    }

}
