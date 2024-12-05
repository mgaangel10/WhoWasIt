package com.example.WhoWasIts.Cuestionario.controller;

import com.example.WhoWasIts.Cuestionario.Dto.*;
import com.example.WhoWasIts.Cuestionario.model.Voto;
import com.example.WhoWasIts.Cuestionario.service.CuestionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CuestionarioController {

    private final CuestionarioService cuestionarioService;

    @PostMapping("usuario/crear/cuestionario")
    public ResponseEntity<CuestionarioDto> crearCuestionario(@RequestBody CrearCuestionario cuestionario){
        CuestionarioDto cuestionarioDto = cuestionarioService.crearCuestionario(cuestionario);
        return ResponseEntity.status(201).body(cuestionarioDto);

    }

    @PostMapping("usuario/crear/opciones/{id}")
    public ResponseEntity<OpcionesDto> addOpciones(@PathVariable UUID id, @RequestBody CrearOpcionesDto cuestionario) {
        OpcionesDto cuestionarioDto = cuestionarioService.addOpciones(id, cuestionario);
        return ResponseEntity.status(201).body(cuestionarioDto);

    }

    @PostMapping("usuario/votar/{id}")
    public ResponseEntity<VotarDto> votar(@PathVariable UUID id){
        VotarDto voto = cuestionarioService.votar(id);
        return ResponseEntity.status(201).body(voto);
    }


    @GetMapping("usuario/ver/resultado/{id}")
    public ResponseEntity<CuestionarioDto> resultadoVoto(@PathVariable UUID id){
        CuestionarioDto cuestionarioDto = cuestionarioService.resultadoVotaciones(id);
        return ResponseEntity.ok(cuestionarioDto);
    }

    @DeleteMapping("usuario/eliminar/opcion/{id}")
    public ResponseEntity<?> eliminarOpcion(@PathVariable UUID id){
        cuestionarioService.eliminarOpciones(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("usuario/todos/cuestioanario/votados")
    public ResponseEntity<List<CuestionarioDto>> todosCuestionarioVotados(){
        List<CuestionarioDto> cuestionarioDtos = cuestionarioService.todosLosResultadosCuestionario();
        return ResponseEntity.ok(cuestionarioDtos);
    }

    @GetMapping("usuario/ver/opciones/{id}")
    public ResponseEntity<List<OpcionesDto>> verOpciones(@PathVariable UUID id){
        List<OpcionesDto> opcionesDtos = cuestionarioService.verOpciones(id);
        return ResponseEntity.ok(opcionesDtos);
    }

    @DeleteMapping("usuario/eliminar/cuestionario/{id}")
    public ResponseEntity<?> eliminarCuestionario(@PathVariable UUID id){
        cuestionarioService.elimianrCuestionario(id);
        return ResponseEntity.noContent().build();
    }





    }
