package com.example.WhoWasIts.FlashPost.model;

import com.example.WhoWasIts.Postear.model.Postear;
import com.example.WhoWasIts.users.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Visualizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Postear postear;

    private LocalDateTime fechaVisualizacion;
}
