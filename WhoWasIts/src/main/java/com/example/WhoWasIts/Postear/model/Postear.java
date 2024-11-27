package com.example.WhoWasIts.Postear.model;

import com.example.WhoWasIts.Comentarios.model.Comentario;
import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import com.example.WhoWasIts.users.model.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Postear {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String contenido;

    @ManyToOne
    @JoinColumn(name = "usuario_anonimo_id")
    private UsuarioAnonimo usuarioAnonimo;

    @OneToMany(mappedBy = "postear")
    @JsonManagedReference
    protected List<Favorito> favoritoList;

    @OneToMany(mappedBy = "postear")
    @JsonManagedReference
    protected List<Comentario> comentarios;

    private String menciones;
    private LocalDateTime fechaHora;
    private String tiempoPublicado;

    @ManyToOne
    private Postear postears;



}
