package com.example.WhoWasIts.Favorito.service;

import com.example.WhoWasIts.Favorito.Dto.FavoritoDto;
import com.example.WhoWasIts.Favorito.Repositorio.FavoritoRepo;
import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.Favorito.model.FavoritoId;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Repositorio.PostearRepo;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoritoService {
    private final FavoritoRepo favoritoRepo;
    private final UsuarioRepo usuarioRepo;
    private final PostearRepo postearRepo;

    public FavoritoDto darAFavorito(UUID postId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario1 = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Postear> postear = postearRepo.findById(postId);
            Optional<Favorito> favorito1 = favoritoRepo.findByUsuarioIdAndPostearId(usuario1.get().getId(), postId);
            if (usuario1.isPresent()&&favorito1.isEmpty()){
                boolean like = true;
                Favorito favorito = Favorito.builder()
                        .id(new FavoritoId(usuario1.get().getId(), postId))
                        .postear(postear.get())
                        .usuario(usuario1.get())
                        .build();
                favoritoRepo.save(favorito);
                return FavoritoDto.of(favorito,like);
            }else {
                Optional<Favorito> favorito = favoritoRepo.findById(new FavoritoId(usuario1.get().getId(), postId));
                favoritoRepo.delete(favorito.get());
                boolean like = false;
                return FavoritoDto.of(favorito.get(),like);
            }
        }
        return null;
    }
}
