export interface ProvinciasResponse {
    idProvincia: string;
    provincia:   string;
    puebloDtos:  PuebloDto[];
}

export interface PuebloDto {
    idPueblo:     string;
    nombrePueblo: string;
}
