export interface VerCharla {
    id:                string;
    nombreUsuario:     string;
    fotourl:           string;
    contenido:         string;
    tiempoPublicado:   null;
    meciones:          string;
    numeroMegustas:    number;
    comentariosDtoLis: ComentariosDtoLi[];
}

export interface ComentariosDtoLi {
    id:            string;
    contenido:     string;
    nombreUsuario: string;
    foto:          string;
}
