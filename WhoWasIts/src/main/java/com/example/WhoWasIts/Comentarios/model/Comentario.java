package com.example.WhoWasIts.Comentarios.model;

import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String contenido;

    @ManyToOne
    @JoinColumn(name = "postear_id")
    private Postear postear;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}
