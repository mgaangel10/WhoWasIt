export interface ResultadoVotacion {
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
