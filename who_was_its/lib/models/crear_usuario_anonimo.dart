class CrearUsuarioAnonimo {
  String? nombreUsuario;
  String? foto;
  String? conocidoComo;

  CrearUsuarioAnonimo({this.nombreUsuario, this.foto, this.conocidoComo});

  CrearUsuarioAnonimo.fromJson(Map<String, dynamic> json) {
    nombreUsuario = json['nombreUsuario'];
    foto = json['foto'];
    conocidoComo = json['conocidoComo'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['nombreUsuario'] = this.nombreUsuario;
    data['foto'] = this.foto;
    data['conocidoComo'] = this.conocidoComo;
    return data;
  }
}
