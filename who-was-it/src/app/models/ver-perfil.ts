export interface VerPerfil {
    id:            string;
    nombreUsuario: string;
    foto:          string;
    usuario:       Usuario;
}

export interface Usuario {
    id:          string;
    username:    string;
    name:        string;
    lastName:    string;
    phoneNumber: string;
    fotoUrl:     string;
}
