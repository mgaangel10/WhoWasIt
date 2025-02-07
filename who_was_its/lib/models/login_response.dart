class LoginResponse {
  String? id;
  String? fullname;
  String? username;
  String? email;
  String? fotoUrl;
  String? usuarioAnonimo;
  String? token;

  LoginResponse(
      {this.id,
      this.fullname,
      this.username,
      this.email,
      this.fotoUrl,
      this.usuarioAnonimo,
      this.token});

  LoginResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    fullname = json['fullname'];
    username = json['username'];
    email = json['email'];
    fotoUrl = json['fotoUrl'];
    usuarioAnonimo = json['usuarioAnonimo'];
    token = json['token'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['fullname'] = this.fullname;
    data['username'] = this.username;
    data['email'] = this.email;
    data['fotoUrl'] = this.fotoUrl;
    data['usuarioAnonimo'] = this.usuarioAnonimo;
    data['token'] = this.token;
    return data;
  }
}
