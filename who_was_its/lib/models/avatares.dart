class Avatares {
  String? id;
  String? imagen;

  Avatares({this.id, this.imagen});

  Avatares.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    imagen = json['imagen'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['imagen'] = this.imagen;
    return data;
  }
}
