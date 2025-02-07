class OpcionesCrear {
  String? opciones;

  OpcionesCrear({this.opciones});

  OpcionesCrear.fromJson(Map<String, dynamic> json) {
    opciones = json['opciones'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['opciones'] = this.opciones;
    return data;
  }
}
