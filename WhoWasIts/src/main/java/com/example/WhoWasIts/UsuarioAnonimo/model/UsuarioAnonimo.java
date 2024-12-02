package com.example.WhoWasIts.UsuarioAnonimo.model;

import com.example.WhoWasIts.users.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioAnonimo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nombreDelUsuario;
    @OneToOne
    private Avatar avatar;

    @OneToOne
    private Usuario usuario;

    private String conocidoComo;

    private String menciones;
}
