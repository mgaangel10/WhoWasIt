package com.example.WhoWasIts.Postear.controller;

import com.example.WhoWasIts.File.service.FileStorageService;
import com.example.WhoWasIts.Postear.Dto.CrearPostDto;
import com.example.WhoWasIts.Postear.Dto.EstadisticasPostDto;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.FlashPost.Dto.VisualizacionDto;
import com.example.WhoWasIts.Postear.Dto.ProvinciasDto;
import com.example.WhoWasIts.Postear.model.Provincias;
import com.example.WhoWasIts.Postear.service.PostearService;
import com.example.WhoWasIts.Postear.service.ProvinciasService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostearController {
    private final PostearService postearService;
    private final ProvinciasService provinciasService;
    private final FileStorageService fileStorageService;


    @PostMapping("usuario/nuevo/post/{id}")
    public ResponseEntity<PostDto> nuevoPost(
            @ModelAttribute CrearPostDto crearPostDto,
            @RequestParam(value = "files", required = false) MultipartFile file,
            @PathVariable UUID id) {

        System.out.println("Archivo recibido: " + (file != null ? file.getOriginalFilename() : "Ninguno"));

        PostDto postDto = postearService.crearPost(crearPostDto, file, id);
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

    @GetMapping("usuario/estadisticas/del/post/{id}")
    public ResponseEntity<EstadisticasPostDto> estadisticasDelPost(@PathVariable UUID id){
        EstadisticasPostDto estadisticasPostDto = postearService.estadisticasPostDto(id);
        return ResponseEntity.ok(estadisticasPostDto);
    }


    @GetMapping("usuario/filtrar/post/{id}")
    public ResponseEntity<List<PostDto>> filtrarPost(@PathVariable UUID id){
        List<PostDto> postDtos = provinciasService.filtrarPorPueblos(id);
        return ResponseEntity.ok(postDtos);
    }


    @GetMapping("usuario/todas/provincias")
    public ResponseEntity<List<ProvinciasDto>> todasProvincias(){
        List<ProvinciasDto> postDtos = provinciasService.todosLosPueblosprovincias();
        return ResponseEntity.ok(postDtos);
    }



    @GetMapping("usuario/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileStorageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }


}
