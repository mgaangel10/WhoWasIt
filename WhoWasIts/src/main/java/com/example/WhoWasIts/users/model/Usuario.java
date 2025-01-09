package com.example.WhoWasIts.users.model;


import com.example.WhoWasIts.Favorito.model.Favorito;
import com.example.WhoWasIts.UsuarioAnonimo.model.UsuarioAnonimo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Usuario extends User {
    @ManyToMany
    @JoinTable(name = "tbl_usuario_usuarios",
            joinColumns = @JoinColumn(name = "responsable_id"),
            inverseJoinColumns = @JoinColumn(name = "usuarios_id"))
    private List<Usuario> usuarios = new ArrayList<>();
    @ManyToMany(mappedBy = "usuarios")
    private List<Usuario> inChargeof;

    @OneToOne
    private UsuarioAnonimo usuarioAnonimo;


    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Favorito> favoritos ;

    private String pais;
    private String provincia;
    private String pueblo;






}
