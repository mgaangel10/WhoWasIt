package com.example.WhoWasIts.users.Dto;


import java.util.UUID;

public record GetUsuario(UUID id,
                         String username,
                         String name,
                         String lastName,
                         String phoneNumber,
                         String fotoUrl) {
}
