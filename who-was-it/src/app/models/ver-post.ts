export interface VerPost {
    id:               string;
    contenido:        string;
    palabraDesordenada: string;
    nombreUsuario:    string;
    foto:             string;
    tiempoPublicado:  null;
    meciones:         string;
    numeroMegustas:   number;
    comentariosDtos:  ComentariosDto[];
    ps:               VerPost | null;
    numeroDeRepost:   number;
    numeroRecomendar: number;
    recomendar:       boolean;
    cuestionarioDto:  CuestionarioDto;
    soloUnaVez:       boolean;
    desorden:         boolean;
    visualizacionDtos: VisualizacionDto[] | null;
    pueblos:            Pueblos;
}

export interface Pueblos {
    id:        string;
    pueblos:   string;
    provincia: Provincia;
}

export interface Provincia {
    id:        string;
    provincia: string;
}

export interface ComentariosDto {
    id:            string;
    contenido:     string;
    nombreUsuario: string;
    foto:          string;
    ganador:       boolean;
}

export interface VisualizacionDto {
    idVisualizacion:      string;
    idPost:               string;
    idUsuario:            string;
    foto:                 string;
    nombreUsuario:        string;
    tiempoPublicado:      string;
    contenido:            string;
    menciones:            string;
    comentariosDtos:      any[];
    numeroDeMegustas:     number;
    numerosDeComentarios: number;
    usarioVeFlashPostDto: UsarioVeFlashPostDto;
}
export interface UsarioVeFlashPostDto{
    loHavisto: boolean;
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
    idCuestionario: string;
    votarDtos:       VotarDto[];
    numeroVotos:     number;
    porcentajeVotos: number;
}

export interface VotarDto {
    getUsuario: GetUsuario;
}

export interface GetUsuario {
    id:          string;
    username:    string;
    name:        string;
    lastName:    string;
    phoneNumber: string;
    fotoUrl:     string;
}
