class ProvinciasResponse {
  String? idProvincia;
  String? provincia;
  List<PuebloDtos>? puebloDtos;

  ProvinciasResponse({this.idProvincia, this.provincia, this.puebloDtos});

  ProvinciasResponse.fromJson(Map<String, dynamic> json) {
    idProvincia = json['idProvincia'];
    provincia = json['provincia'];
    if (json['puebloDtos'] != null) {
      puebloDtos = <PuebloDtos>[];
      json['puebloDtos'].forEach((v) {
        puebloDtos!.add(new PuebloDtos.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['idProvincia'] = this.idProvincia;
    data['provincia'] = this.provincia;
    if (this.puebloDtos != null) {
      data['puebloDtos'] = this.puebloDtos!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class PuebloDtos {
  String? idPueblo;
  String? nombrePueblo;

  PuebloDtos({this.idPueblo, this.nombrePueblo});

  PuebloDtos.fromJson(Map<String, dynamic> json) {
    idPueblo = json['idPueblo'];
    nombrePueblo = json['nombrePueblo'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['idPueblo'] = this.idPueblo;
    data['nombrePueblo'] = this.nombrePueblo;
    return data;
  }
}
