package com.example.WhoWasIts.FavoritoCharla.Dto;

import com.example.WhoWasIts.Charlas.Dto.CharlasDto;
import com.example.WhoWasIts.Favorito.Dto.FavoritoDto;
import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.FavoritoCharla.model.FavoritoCharla;
import com.example.WhoWasIts.Postear.Dto.PostDto;

public record FavoritoCharlaDto(boolean isLike,
                                CharlasDto postDto) {
    public static FavoritoCharlaDto of(FavoritoCharla f, boolean like){
        return new FavoritoCharlaDto(
                like,
                CharlasDto.of(f.getCharla())

        );
    }
}