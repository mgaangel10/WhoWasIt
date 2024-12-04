export interface OpcionesResponse {
    id:         string;
    titulo:     string;
    totalVotos: number;
    opciones:   Opcione[];
}

export interface Opcione {
    id:              string;
    opcion:          string;
    votos:           any[] | null;
    numeroVotos:     number;
    porcentajeVotos: number;
}
