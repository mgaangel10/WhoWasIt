package com.example.WhoWasIts.users.Dto;


import com.example.WhoWasIts.users.model.Administrador;
import com.example.WhoWasIts.users.model.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtUserResponse extends PostRegistroDto{

    private String token;

    public JwtUserResponse(PostRegistroDto postRegistroDto) {
        id = postRegistroDto.getId();
        fullname = postRegistroDto.getFullname();
        username = postRegistroDto.getUsername();
        email = postRegistroDto.getEmail();
        fotoUrl = postRegistroDto.getFotoUrl(); // Asegúrate de que no sea null
        usuarioAnonimo = postRegistroDto.getUsuarioAnonimo(); // Manejado en PostRegistroDto
        creadoen = postRegistroDto.getCreadoen();
        birthdate = postRegistroDto.getBirthdate();
    }


    public  static  JwtUserResponse ofUsuario (Usuario usuario, String token){
        JwtUserResponse jwtUserResponse= new JwtUserResponse(PostRegistroDto.Usuario(usuario));
        jwtUserResponse.setToken(token);
        return jwtUserResponse;
    }

    public static JwtUserResponse ofAdminsitrador(Administrador administrador, String token){
        JwtUserResponse jwtUserResponse = new JwtUserResponse(PostRegistroDto.Administrador(administrador));
        jwtUserResponse.setToken(token);
        return jwtUserResponse;
    }
}
