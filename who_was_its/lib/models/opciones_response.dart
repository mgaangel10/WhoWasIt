class OpcionesResponse {
  String? id;
  String? opcion;
  String? idCuestionario;
  Null? votarDtos;
  int? numeroVotos;
  int? porcentajeVotos;

  OpcionesResponse(
      {this.id,
      this.opcion,
      this.idCuestionario,
      this.votarDtos,
      this.numeroVotos,
      this.porcentajeVotos});

  OpcionesResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    opcion = json['opcion'];
    idCuestionario = json['idCuestionario'];
    votarDtos = json['votarDtos'];
    numeroVotos = json['numeroVotos'];
    porcentajeVotos = json['porcentajeVotos'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['opcion'] = this.opcion;
    data['idCuestionario'] = this.idCuestionario;
    data['votarDtos'] = this.votarDtos;
    data['numeroVotos'] = this.numeroVotos;
    data['porcentajeVotos'] = this.porcentajeVotos;
    return data;
  }
}
