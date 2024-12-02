export interface DarMegusta {
    isLike:  boolean;
    postDto: PostDto;
}

export interface PostDto {
    id:              string;
    contenido:       string;
    nombreUsuario:   string;
    foto:            string;
    tiempoPublicado: null;
    meciones:        string;
    numeroMegustas:  number;
    comentariosDtos: ComentariosDto[];
    ps:              PostDto | null;
    numeroDeRepost:  number;
}

export interface ComentariosDto {
    id:            string;
    contenido:     string;
    nombreUsuario: string;
    foto:          string;
}
