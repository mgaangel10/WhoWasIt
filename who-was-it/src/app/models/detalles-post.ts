export interface DetallesPost {
    id:               string;
    contenido:        string;
    palabraDesordenada: string;
    nombreUsuario:    string;
    foto:             string;
    tiempoPublicado:  null;
    meciones:         string;
    numeroMegustas:   number;
    comentariosDtos:  ComentariosDto[];
    ps:               DetallesPost | null;
    numeroDeRepost:   number;
    numeroRecomendar: number;
    recomendar:       boolean;
    cuestionarioDto:  CuestionarioDto;
    desorden:         boolean;

}

export interface ComentariosDto {
    id:            string;
    contenido:     string;
    nombreUsuario: string;
    foto:          string;
}

export interface CuestionarioDto {
    id:         string;
    titulo:     string;
    totalVotos: number;
    opciones:   Opcione[];
}

export interface Opcione {
    id:              string;
    opcion:          string;
    idCuestionario:  string;
    votarDtos:       VotarDto[];
    numeroVotos:     number;
    porcentajeVotos: number;
}

export interface VotarDto {
    idCuestionario: string;
    getUsuario:     GetUsuario;
}

export interface GetUsuario {
    id:          string;
    username:    string;
    name:        string;
    lastName:    string;
    phoneNumber: string;
    fotoUrl:     string;
}
