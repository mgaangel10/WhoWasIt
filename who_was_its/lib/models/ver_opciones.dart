class VerOpciones {
  String? id;
  String? opcion;
  String? idCuestionario;

  int? numeroVotos;
  int? porcentajeVotos;

  VerOpciones(
      {this.id,
      this.opcion,
      this.idCuestionario,
      this.numeroVotos,
      this.porcentajeVotos});

  VerOpciones.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    opcion = json['opcion'];
    idCuestionario = json['idCuestionario'];

    numeroVotos = json['numeroVotos'];
    porcentajeVotos = json['porcentajeVotos'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['opcion'] = this.opcion;
    data['idCuestionario'] = this.idCuestionario;

    data['numeroVotos'] = this.numeroVotos;
    data['porcentajeVotos'] = this.porcentajeVotos;
    return data;
  }
}
