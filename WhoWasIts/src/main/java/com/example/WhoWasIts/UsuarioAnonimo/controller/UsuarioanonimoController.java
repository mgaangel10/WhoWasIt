package com.example.WhoWasIts.UsuarioAnonimo.controller;

import com.example.WhoWasIts.UsuarioAnonimo.Dto.CrearUsuarioAnoninoDto;
import com.example.WhoWasIts.UsuarioAnonimo.Dto.UsuarioAnonimoDto;
import com.example.WhoWasIts.UsuarioAnonimo.service.UsuarioAnonimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioanonimoController {


    private final UsuarioAnonimoService usuarioAnonimoService;


    @PostMapping("usuario/crear/usuario/anonimo")
    public ResponseEntity<UsuarioAnonimoDto> crearUsuario(@RequestBody CrearUsuarioAnoninoDto crearUsuarioAnoninoDto){
        UsuarioAnonimoDto usuarioAnonimoDto = usuarioAnonimoService.crearUsuarioAnonimo(crearUsuarioAnoninoDto);
        return ResponseEntity.status(201).body(usuarioAnonimoDto);
    }
}
