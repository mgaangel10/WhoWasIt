export interface VerPost {
    id:              string;
    contenido:       string;
    nombreUsuario:   string;
    foto:            string;
    tiempoPublicado: null;
    meciones:        string;
    numeroMegustas:  number;
    comentariosDtos: ComentariosDto[];
    ps:              VerPost | null;
    numeroDeRepost:  number;
}

export interface ComentariosDto {
    id:            string;
    contenido:     string;
    nombreUsuario: string;
    foto:          string;
}
