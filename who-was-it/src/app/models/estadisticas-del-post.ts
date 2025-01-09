export interface EstadisticasDelPost {
    id:                        string;
    numeroMegusta:             number;
    numeroDeCOmentarios:       number;
    detallesFavoritosDtos:     DetallesFavoritosDto[];
    detallesDeComentariosDtos: DetallesDeComentariosDto[];
    cuestionarioDto:           CuestionarioDto;
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

export interface DetallesDeComentariosDto {
    nombreUsario: string;
    foto:         string;
}

export interface DetallesFavoritosDto {
    nombreUsuario: string;
    foto:          string;
}
