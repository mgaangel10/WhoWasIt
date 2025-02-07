class CuestionarioResponse {
  String? id;
  String? titulo;
  int? totalVotos;
  Null? opciones;

  CuestionarioResponse({this.id, this.titulo, this.totalVotos, this.opciones});

  CuestionarioResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    titulo = json['titulo'];
    totalVotos = json['totalVotos'];
    opciones = json['opciones'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['titulo'] = this.titulo;
    data['totalVotos'] = this.totalVotos;
    data['opciones'] = this.opciones;
    return data;
  }
}
