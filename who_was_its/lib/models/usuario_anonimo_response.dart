class UsuarioAnonimoResponse {
  String? id;
  String? nombreUsuario;
  String? foto;
  Usuario? usuario;

  UsuarioAnonimoResponse(
      {this.id, this.nombreUsuario, this.foto, this.usuario});

  UsuarioAnonimoResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    nombreUsuario = json['nombreUsuario'];
    foto = json['foto'];
    usuario =
        json['usuario'] != null ? new Usuario.fromJson(json['usuario']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['nombreUsuario'] = this.nombreUsuario;
    data['foto'] = this.foto;
    if (this.usuario != null) {
      data['usuario'] = this.usuario!.toJson();
    }
    return data;
  }
}

class Usuario {
  String? id;
  String? username;
  String? name;
  String? lastName;
  String? phoneNumber;
  String? fotoUrl;

  Usuario(
      {this.id,
      this.username,
      this.name,
      this.lastName,
      this.phoneNumber,
      this.fotoUrl});

  Usuario.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    username = json['username'];
    name = json['name'];
    lastName = json['lastName'];
    phoneNumber = json['phoneNumber'];
    fotoUrl = json['fotoUrl'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['username'] = this.username;
    data['name'] = this.name;
    data['lastName'] = this.lastName;
    data['phoneNumber'] = this.phoneNumber;
    data['fotoUrl'] = this.fotoUrl;
    return data;
  }
}
