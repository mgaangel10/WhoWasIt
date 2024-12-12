package com.example.WhoWasIts.Charlas.model;

import com.example.WhoWasIts.Comentarios.model.Comentario;
import com.example.WhoWasIts.Cuestionario.model.Cuestionario;
import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.FavoritoCharla.model.FavoritoCharla;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Charlas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY) // Evita cargar la información del usuario hasta que se necesite
    @JoinColumn(name = "usuario_anonimo_id", nullable = false)
    @JsonBackReference // Evita referencias circulares en la serialización
    private UsuarioAnonimo usuarioAnonimo;

    @OneToMany(mappedBy = "charla", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Indica que este lado de la relación se serializa
    private List<FavoritoCharla> favoritoList = new ArrayList<>();

    @OneToMany(mappedBy = "charla", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Comentario> comentarios = new ArrayList<>();

    private String menciones;



    @Column(nullable = false)
    private LocalDateTime fechaHora;

    private String tiempoPublicado;





}
