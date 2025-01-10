package com.example.WhoWasIts.Postear.model;

import com.example.WhoWasIts.Comentarios.model.Comentario;
import com.example.WhoWasIts.Cuestionario.model.Cuestionario;
import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.FlashPost.model.Visualizacion;
import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
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
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Postear {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY) // Evita cargar la información del usuario hasta que se necesite
    @JoinColumn(name = "usuario_anonimo_id", nullable = false)
    @JsonBackReference // Evita referencias circulares en la serialización
    private UsuarioAnonimo usuarioAnonimo;

    @OneToMany(mappedBy = "postear", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Indica que este lado de la relación se serializa
    private List<Favorito> favoritoList = new ArrayList<>();

    @OneToMany(mappedBy = "postear", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comentario> comentarios = new ArrayList<>();

    private String menciones;
    private boolean recomendar;
    @OneToMany(mappedBy = "postears", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Relación bidireccional para rastrear reposts
    private List<Postear> recomendars = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    private String tiempoPublicado;

    @ManyToOne(fetch = FetchType.LAZY) // Evita cargar toda la relación hasta que sea necesario
    @JsonBackReference // Para evitar ciclos infinitos en los reposts
    private Postear postears;

    @OneToMany(mappedBy = "postears", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Relación bidireccional para rastrear reposts
    private List<Postear> reposts = new ArrayList<>();

    private boolean postUnaSolaVez;

    @OneToMany
    @JsonBackReference
    private List<Visualizacion> visualizacions;

    private boolean palabrasDesordenadas;

    private String palabraDesordenada;

    @OneToOne
    private Pueblos pueblos;


    @OneToOne
    private Cuestionario cuestionario;
}
