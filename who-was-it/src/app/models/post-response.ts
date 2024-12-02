export interface PostResponse {
    id:              string;
    contenido:       string;
    nombreUsuario:   string;
    foto:            string;
    tiempoPublicado: null;
    meciones:        string;
    numeroMegustas:  number;
    comentariosDtos: any[];
    ps:              PostResponse | null;
}
