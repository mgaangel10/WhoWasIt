package com.example.WhoWasIts.Favorito.Dto;

import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.Postear.Dto.PostDto;

public record FavoritoDto(boolean isLike,
                          PostDto postDto) {
    public static FavoritoDto of(Favorito f, boolean like){
        return new FavoritoDto(
                like,
                PostDto.of(f.getPostear())
        );
    }
}