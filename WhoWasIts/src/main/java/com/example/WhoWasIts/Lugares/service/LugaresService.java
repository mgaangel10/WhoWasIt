package com.example.WhoWasIts.Lugares.service;

import com.example.WhoWasIts.Lugares.Dto.LugarAndPuebloDto;
import com.example.WhoWasIts.Lugares.Dto.LugarBuscarDto;
import com.example.WhoWasIts.Lugares.Dto.LugaresAndPueblosDto;
import com.example.WhoWasIts.Lugares.Dto.LugaresDto;
import com.example.WhoWasIts.Lugares.model.Lugares;
import com.example.WhoWasIts.Lugares.repositorio.LugaresRepo;
import com.example.WhoWasIts.Postear.Dto.PostDto;
import com.example.WhoWasIts.Postear.Dto.ProvinciasDto;
import com.example.WhoWasIts.Postear.Dto.PuebloDto;
import com.example.WhoWasIts.Postear.Repositorio.ProvinciasRepo;
import com.example.WhoWasIts.Postear.Repositorio.PueblosRepo;
import com.example.WhoWasIts.Postear.model.Provincias;
import com.example.WhoWasIts.Postear.model.Pueblos;
import com.example.WhoWasIts.Postear.service.PostearService;
import com.example.WhoWasIts.Postear.service.ProvinciasService;
import com.example.WhoWasIts.users.model.Usuario;
import com.example.WhoWasIts.users.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class LugaresService {

    private final UsuarioRepo usuarioRepo;
    private final LugaresRepo lugaresRepo;
    private final PueblosRepo pueblosRepo;
    private final ProvinciasService provinciasService;

    public String buscarEnELBuscador(String lugarBuscarDto){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre= ((UserDetails)principal).getUsername();
            Optional<Pueblos> pueblos = pueblosRepo.findByPueblos(lugarBuscarDto);
            Optional<Lugares> lugares = lugaresRepo.findByNombreLugar(lugarBuscarDto);
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            System.out.println("lugar es nulo" +lugarBuscarDto);



            if (pueblos.isPresent()){
                System.out.println("lo devuelve purbl0");
                return pueblos.get().getPueblos();
            }
            if (lugares.isPresent()){
                System.out.println("lo devuelve lugar");
                return lugares.get().getNombreLugar();
            }
            if (lugares.isEmpty()) {
                System.out.println("lo crea");
                Lugares lugares1 = new Lugares();
                lugares1.setNombreLugar(lugarBuscarDto);
                lugaresRepo.save(lugares1);

                return lugares1.getNombreLugar();
            }
        }
        return null;
    }

    public LugaresAndPueblosDto todos(){

        List<Pueblos> pueblos = pueblosRepo.findAll();
        List<Lugares> lugares = lugaresRepo.findAll();
        List<PuebloDto> puebloDtos = pueblos.stream().map(PuebloDto::of).collect(Collectors.toList());
        List<LugaresDto> lugaresDtos = lugares.stream().map(LugaresDto::of).collect(Collectors.toList());
        return LugaresAndPueblosDto.of(lugaresDtos,puebloDtos);


    }
}
