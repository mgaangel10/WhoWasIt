export interface VotarResponse {
    idCuestionario:string;
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
