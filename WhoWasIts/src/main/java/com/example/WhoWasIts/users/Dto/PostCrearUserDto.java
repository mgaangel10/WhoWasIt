package com.example.WhoWasIts.users.Dto;




import java.time.LocalDate;
import java.util.EnumSet;

public record PostCrearUserDto(String email,
                               String name,
                               String lastName,
                               String pais,
                               String provincia,
                               String pueblo,
                               String password,
                               String fotoUrl,
                               LocalDate nacimiento,
                               String phoneNumber) {
}
