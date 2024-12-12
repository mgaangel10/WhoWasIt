export interface PostUnaVezResponse {
    idVisualizacion:      string;
    idPost:               string;
    idUsuario:            string;
    foto:                 string;
    nombreUsuario:        string;
    tiempoPublicado:      string;
    contenido:            string;
    menciones:            string;
    comentariosDtos:      ComentariosDto[];
    numeroDeMegustas:     number;
    numerosDeComentarios: number;
    loHaVisto:            boolean;
}

export interface ComentariosDto {
    id:            string;
    contenido:     string;
    nombreUsuario: string;
    foto:          string;
}
