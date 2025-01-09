package com.example.WhoWasIts.UsuarioAnonimo.controller;

import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.UsuarioAnonimo.Dto.CrearUsuarioAnoninoDto;
import com.example.WhoWasIts.UsuarioAnonimo.Dto.UsuarioAnonimoDto;
import com.example.WhoWasIts.UsuarioAnonimo.model.Avatar;
import com.example.WhoWasIts.UsuarioAnonimo.repositorio.AvatarRepo;
import com.example.WhoWasIts.UsuarioAnonimo.service.UsuarioAnonimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioanonimoController {


    private final UsuarioAnonimoService usuarioAnonimoService;


    @PostMapping("usuario/crear/usuario/anonimo")
    public ResponseEntity<UsuarioAnonimoDto> crearUsuario(@RequestBody CrearUsuarioAnoninoDto crearUsuarioAnoninoDto){
        UsuarioAnonimoDto usuarioAnonimoDto = usuarioAnonimoService.crearUsuarioAnonimo(crearUsuarioAnoninoDto);
        return ResponseEntity.status(201).body(usuarioAnonimoDto);
    }

    @GetMapping("usuario/ver/perfil")
    public ResponseEntity<UsuarioAnonimoDto> verPerfil(){
        UsuarioAnonimoDto usuarioAnonimoDto = usuarioAnonimoService.verPerfil();
        return ResponseEntity.ok(usuarioAnonimoDto);
    }

    @GetMapping("usuario/ver/menciones")
    public ResponseEntity<List<PostDto>> verMenciones(){
        List<PostDto> postDtos = usuarioAnonimoService.verMenciones();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("usuario/todos/avatares")
    public ResponseEntity<List<Avatar>> todosAvatares(){
        List<Avatar> avatars = usuarioAnonimoService.todosLOsAvatares();
        return ResponseEntity.ok(avatars);
    }
}
