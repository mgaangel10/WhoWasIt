import 'package:who_was_its/models/ver_post.dart';

class CrearPost {
  String? id;
  String? contenido;
  String? palabraDesordenada;
  String? nombreUsuario;
  String? foto;
  String? tiempoPublicado;
  String? menciones;
  int? numeroMegustas;
  List<dynamic>?
      comentariosDtos; // Usa `dynamic` si los elementos pueden variar.
  int? numeroDeRepost;
  int? numeroRecomendar;
  bool? recomendar;
  CuestionarioDto? cuestionarioDto;
  bool? soloUnaVez;
  bool? desorden;
  List<dynamic>?
      visualizacionDtos; // Maneja visualizaciones como listas dinámicas.
  Pueblos? pueblos;

  CrearPost({
    this.id,
    this.contenido,
    this.palabraDesordenada,
    this.nombreUsuario,
    this.foto,
    this.tiempoPublicado,
    this.menciones,
    this.numeroMegustas,
    this.comentariosDtos,
    this.numeroDeRepost,
    this.numeroRecomendar,
    this.recomendar,
    this.cuestionarioDto,
    this.soloUnaVez,
    this.desorden,
    this.visualizacionDtos,
    this.pueblos,
  });

  CrearPost.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    contenido = json['contenido'];
    palabraDesordenada = json['palabraDesordenada'];
    nombreUsuario = json['nombreUsuario'];
    foto = json['foto'];
    tiempoPublicado = json['tiempoPublicado'];
    menciones = json['menciones'];
    numeroMegustas = json['numeroMegustas'];
    comentariosDtos =
        json['comentariosDtos'] ?? []; // Asegura una lista vacía si es nula.
    numeroDeRepost = json['numeroDeRepost'];
    numeroRecomendar = json['numeroRecomendar'];
    recomendar = json['recomendar'];
    cuestionarioDto = json['cuestionarioDto'] != null
        ? CuestionarioDto.fromJson(json['cuestionarioDto'])
        : null;
    soloUnaVez = json['soloUnaVez'];
    desorden = json['desorden'];
    visualizacionDtos = json['visualizacionDtos'] ?? [];
    pueblos =
        json['pueblos'] != null ? Pueblos.fromJson(json['pueblos']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = {};
    data['id'] = id;
    data['contenido'] = contenido;
    data['palabraDesordenada'] = palabraDesordenada;
    data['nombreUsuario'] = nombreUsuario;
    data['foto'] = foto;
    data['tiempoPublicado'] = tiempoPublicado;
    data['menciones'] = menciones;
    data['numeroMegustas'] = numeroMegustas;
    if (comentariosDtos != null) {
      data['comentariosDtos'] = comentariosDtos;
    }
    data['numeroDeRepost'] = numeroDeRepost;
    data['numeroRecomendar'] = numeroRecomendar;
    data['recomendar'] = recomendar;
    if (cuestionarioDto != null) {
      data['cuestionarioDto'] = cuestionarioDto!.toJson();
    }
    data['soloUnaVez'] = soloUnaVez;
    data['desorden'] = desorden;
    if (visualizacionDtos != null) {
      data['visualizacionDtos'] = visualizacionDtos;
    }
    if (pueblos != null) {
      data['pueblos'] = pueblos!.toJson();
    }
    return data;
  }
}

// Las clases CuestionarioDto, Opciones, Pueblos y Provincia también se ajustan con la misma lógica.
