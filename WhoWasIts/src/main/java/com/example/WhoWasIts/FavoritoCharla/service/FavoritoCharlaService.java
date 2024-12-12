package com.example.WhoWasIts.FavoritoCharla.service;

import com.example.WhoWasIts.Charlas.model.Charlas;
import com.example.WhoWasIts.Charlas.repositorio.CharlaRepo;
import com.example.WhoWasIts.FavoritoCharla.Dto.FavoritoCharlaDto;
import com.example.WhoWasIts.FavoritoCharla.model.FavoritoCharla;
import com.example.WhoWasIts.FavoritoCharla.model.FavoritoIdCharla;
import com.example.WhoWasIts.FavoritoCharla.repositorio.FavoritoCharlaRepo;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoritoCharlaService {

    private final FavoritoCharlaRepo favoritoRepo;
    private final UsuarioRepo usuarioRepo;
    private final CharlaRepo charlaRepo;

    public FavoritoCharlaDto darAFavorito(UUID postId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Usuario> usuario1 = usuarioRepo.findByEmailIgnoreCase(nombre);
            Optional<Charlas> charlas = charlaRepo.findById(postId);
            Optional<FavoritoCharla> favorito1 = favoritoRepo.findByUsuarioIdAndCharlaId(usuario1.get().getId(), postId);
            if (usuario1.isPresent()&&favorito1.isEmpty()){
                boolean like = true;
                FavoritoCharla favorito = FavoritoCharla.builder()
                        .id(new FavoritoIdCharla(usuario1.get().getId(), postId))
                        .charla(charlas.get())
                        .usuario(usuario1.get())
                        .build();
                favoritoRepo.save(favorito);
                return FavoritoCharlaDto.of(favorito,like);
            }else {
                Optional<FavoritoCharla> favorito = favoritoRepo.findById(new FavoritoIdCharla(usuario1.get().getId(), postId));
                favoritoRepo.delete(favorito.get());
                boolean like = false;
                return FavoritoCharlaDto.of(favorito.get(),like);
            }
        }
        return null;
    }
}
