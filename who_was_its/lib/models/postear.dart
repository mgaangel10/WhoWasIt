class Postear {
  String? contenido;
  String? id;
  String? idCuestionario;
  bool? postUnaVez;
  bool? desorden;

  Postear(
      {this.contenido,
      this.id,
      this.idCuestionario,
      this.postUnaVez,
      this.desorden});

  Postear.fromJson(Map<String, dynamic> json) {
    contenido = json['contenido'];
    id = json['id'];
    idCuestionario = json['idCuestionario'];
    postUnaVez = json['postUnaVez'];
    desorden = json['desorden'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['contenido'] = this.contenido;
    data['id'] = this.id;
    data['idCuestionario'] = this.idCuestionario;
    data['postUnaVez'] = this.postUnaVez;
    data['desorden'] = this.desorden;
    return data;
  }
}
