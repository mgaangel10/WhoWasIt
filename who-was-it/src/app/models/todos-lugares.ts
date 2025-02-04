export interface LugaresAll {
    lugares: Lugare[];
    pueblos: Pueblo[];
}

export interface Lugare {
    id:          string;
    nombreLugar: string;
}

export interface Pueblo {
    idPueblo:     string;
    nombrePueblo: string;
}
