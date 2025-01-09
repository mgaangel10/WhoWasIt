package com.example.WhoWasIts.FlashPost.Dto;

public record UsarioVeFlashPostDto(Boolean loHavisto) {
    public static UsarioVeFlashPostDto of(Boolean loHavisto){
        return new UsarioVeFlashPostDto(
                loHavisto
        );
    }
}
