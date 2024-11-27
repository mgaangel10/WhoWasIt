package com.example.WhoWasIts.users.controller;


import com.example.WhoWasIts.security.jwt.JwtProvider;
import com.example.WhoWasIts.users.Dto.*;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.service.UsuarioService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("auth/register/user")
    public ResponseEntity<?> crearUser(@RequestBody PostCrearUserDto postCrearUserDto) {
        try {
            Usuario usuario = usuarioService.createWithRole(postCrearUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Usuario(usuario));
        } catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }



    @PostMapping("/auth/login/user")
    public ResponseEntity<JwtUserResponse> loginUser(@RequestBody PostLogin postLogin){
        usuarioService.setearEnabled(postLogin);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLogin.email(),
                        postLogin.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.ofUsuario(usuario, token));
    }

    @GetMapping("usuario/ver/detalles/{id}")
    public ResponseEntity<GetUsuario> verDetallesUsuario(@PathVariable UUID id){
        GetUsuario usuario1 = usuarioService.getUsuario(id);
        return ResponseEntity.ok(usuario1);
    }
}
