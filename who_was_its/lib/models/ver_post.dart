class VerPost {
  String? id;
  String? contenido;
  String? palabraDesordenada;
  String? nombreUsuario;
  String? foto;
  String? tiempoPublicado;
  String? meciones;
  int? numeroMegustas;
  List<ComentariosDtos>? comentariosDtos;
  Ps? ps;
  int? numeroDeRepost;
  int? numeroRecomendar;
  bool? recomendar;
  CuestionarioDto? cuestionarioDto;
  bool? soloUnaVez;
  bool? desorden;
  List<VisualizacionDtos>? visualizacionDtos;
  Pueblos? pueblos;

  VerPost(
      {this.id,
      this.contenido,
      this.palabraDesordenada,
      this.nombreUsuario,
      this.foto,
      this.tiempoPublicado,
      this.meciones,
      this.numeroMegustas,
      this.comentariosDtos,
      this.ps,
      this.numeroDeRepost,
      this.numeroRecomendar,
      this.recomendar,
      this.cuestionarioDto,
      this.soloUnaVez,
      this.desorden,
      this.visualizacionDtos,
      this.pueblos});

  VerPost.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    contenido = json['contenido'];
    palabraDesordenada = json['palabraDesordenada'];
    nombreUsuario = json['nombreUsuario'];
    foto = json['foto'];
    tiempoPublicado = json['tiempoPublicado'];
    meciones = json['meciones'];
    numeroMegustas = json['numeroMegustas'];
    if (json['comentariosDtos'] != null) {
      comentariosDtos = <ComentariosDtos>[];
      json['comentariosDtos'].forEach((v) {
        comentariosDtos!.add(new ComentariosDtos.fromJson(v));
      });
    }
    ps = json['ps'] != null ? new Ps.fromJson(json['ps']) : null;
    numeroDeRepost = json['numeroDeRepost'];
    numeroRecomendar = json['numeroRecomendar'];
    recomendar = json['recomendar'];
    cuestionarioDto = json['cuestionarioDto'] != null
        ? new CuestionarioDto.fromJson(json['cuestionarioDto'])
        : null;
    soloUnaVez = json['soloUnaVez'];
    desorden = json['desorden'];
    if (json['visualizacionDtos'] != null) {
      visualizacionDtos = <VisualizacionDtos>[];
      json['visualizacionDtos'].forEach((v) {
        visualizacionDtos!.add(new VisualizacionDtos.fromJson(v));
      });
    }
    pueblos =
        json['pueblos'] != null ? new Pueblos.fromJson(json['pueblos']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['contenido'] = this.contenido;
    data['palabraDesordenada'] = this.palabraDesordenada;
    data['nombreUsuario'] = this.nombreUsuario;
    data['foto'] = this.foto;
    data['tiempoPublicado'] = this.tiempoPublicado;
    data['meciones'] = this.meciones;
    data['numeroMegustas'] = this.numeroMegustas;
    if (this.comentariosDtos != null) {
      data['comentariosDtos'] =
          this.comentariosDtos!.map((v) => v.toJson()).toList();
    }
    if (this.ps != null) {
      data['ps'] = this.ps!.toJson();
    }
    data['numeroDeRepost'] = this.numeroDeRepost;
    data['numeroRecomendar'] = this.numeroRecomendar;
    data['recomendar'] = this.recomendar;
    if (this.cuestionarioDto != null) {
      data['cuestionarioDto'] = this.cuestionarioDto!.toJson();
    }
    data['soloUnaVez'] = this.soloUnaVez;
    data['desorden'] = this.desorden;
    if (this.visualizacionDtos != null) {
      data['visualizacionDtos'] =
          this.visualizacionDtos!.map((v) => v.toJson()).toList();
    }
    if (this.pueblos != null) {
      data['pueblos'] = this.pueblos!.toJson();
    }
    return data;
  }
}

class ComentariosDtos {
  String? id;
  String? contenido;
  String? nombreUsuario;
  String? foto;
  bool? ganador;

  ComentariosDtos(
      {this.id, this.contenido, this.nombreUsuario, this.foto, this.ganador});

  ComentariosDtos.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    contenido = json['contenido'];
    nombreUsuario = json['nombreUsuario'];
    foto = json['foto'];
    ganador = json['ganador'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['contenido'] = this.contenido;
    data['nombreUsuario'] = this.nombreUsuario;
    data['foto'] = this.foto;
    data['ganador'] = this.ganador;
    return data;
  }
}

class Ps {
  String? id;
  String? contenido;
  Null? palabraDesordenada;
  String? nombreUsuario;
  String? foto;
  String? tiempoPublicado;
  String? meciones;
  int? numeroMegustas;
  List<ComentariosDtos>? comentariosDtos;
  Null? ps;
  int? numeroDeRepost;
  int? numeroRecomendar;
  bool? recomendar;
  CuestionarioDto? cuestionarioDto;
  bool? soloUnaVez;
  bool? desorden;
  List<VisualizacionDtos>? visualizacionDtos;
  Pueblos? pueblos;

  Ps(
      {this.id,
      this.contenido,
      this.palabraDesordenada,
      this.nombreUsuario,
      this.foto,
      this.tiempoPublicado,
      this.meciones,
      this.numeroMegustas,
      this.comentariosDtos,
      this.ps,
      this.numeroDeRepost,
      this.numeroRecomendar,
      this.recomendar,
      this.cuestionarioDto,
      this.soloUnaVez,
      this.desorden,
      this.visualizacionDtos,
      this.pueblos});

  Ps.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    contenido = json['contenido'];
    palabraDesordenada = json['palabraDesordenada'];
    nombreUsuario = json['nombreUsuario'];
    foto = json['foto'];
    tiempoPublicado = json['tiempoPublicado'];
    meciones = json['meciones'];
    numeroMegustas = json['numeroMegustas'];
    if (json['comentariosDtos'] != null) {
      comentariosDtos = <ComentariosDtos>[];
      json['comentariosDtos'].forEach((v) {
        comentariosDtos!.add(new ComentariosDtos.fromJson(v));
      });
    }
    ps = json['ps'];
    numeroDeRepost = json['numeroDeRepost'];
    numeroRecomendar = json['numeroRecomendar'];
    recomendar = json['recomendar'];
    cuestionarioDto = json['cuestionarioDto'] != null
        ? new CuestionarioDto.fromJson(json['cuestionarioDto'])
        : null;
    soloUnaVez = json['soloUnaVez'];
    desorden = json['desorden'];
    if (json['visualizacionDtos'] != null) {
      visualizacionDtos = <VisualizacionDtos>[];
      json['visualizacionDtos'].forEach((v) {
        visualizacionDtos!.add(new VisualizacionDtos.fromJson(v));
      });
    }
    pueblos =
        json['pueblos'] != null ? new Pueblos.fromJson(json['pueblos']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['contenido'] = this.contenido;
    data['palabraDesordenada'] = this.palabraDesordenada;
    data['nombreUsuario'] = this.nombreUsuario;
    data['foto'] = this.foto;
    data['tiempoPublicado'] = this.tiempoPublicado;
    data['meciones'] = this.meciones;
    data['numeroMegustas'] = this.numeroMegustas;
    if (this.comentariosDtos != null) {
      data['comentariosDtos'] =
          this.comentariosDtos!.map((v) => v.toJson()).toList();
    }
    data['ps'] = this.ps;
    data['numeroDeRepost'] = this.numeroDeRepost;
    data['numeroRecomendar'] = this.numeroRecomendar;
    data['recomendar'] = this.recomendar;
    if (this.cuestionarioDto != null) {
      data['cuestionarioDto'] = this.cuestionarioDto!.toJson();
    }
    data['soloUnaVez'] = this.soloUnaVez;
    data['desorden'] = this.desorden;
    if (this.visualizacionDtos != null) {
      data['visualizacionDtos'] =
          this.visualizacionDtos!.map((v) => v.toJson()).toList();
    }
    if (this.pueblos != null) {
      data['pueblos'] = this.pueblos!.toJson();
    }
    return data;
  }
}

class CuestionarioDto {
  String? id;
  String? titulo;
  int? totalVotos;
  List<Opciones>? opciones;

  CuestionarioDto({this.id, this.titulo, this.totalVotos, this.opciones});

  CuestionarioDto.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    titulo = json['titulo'];
    totalVotos = json['totalVotos'];
    if (json['opciones'] != null) {
      opciones = <Opciones>[];
      json['opciones'].forEach((v) {
        opciones!.add(new Opciones.fromJson(v));
      });
    }
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['titulo'] = this.titulo;
    data['totalVotos'] = this.totalVotos;
    if (this.opciones != null) {
      data['opciones'] = this.opciones!.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class Opciones {
  String? id;
  String? opcion;
  String? idCuestionario;
  List<VotarDtos>? votarDtos;
  int? numeroVotos;
  int? porcentajeVotos;

  Opciones(
      {this.id,
      this.opcion,
      this.idCuestionario,
      this.votarDtos,
      this.numeroVotos,
      this.porcentajeVotos});

  Opciones.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    opcion = json['opcion'];
    idCuestionario = json['idCuestionario'];
    if (json['votarDtos'] != null) {
      votarDtos = <VotarDtos>[];
      json['votarDtos'].forEach((v) {
        votarDtos!.add(new VotarDtos.fromJson(v));
      });
    }
    numeroVotos = json['numeroVotos'];
    porcentajeVotos = json['porcentajeVotos'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['opcion'] = this.opcion;
    data['idCuestionario'] = this.idCuestionario;
    if (this.votarDtos != null) {
      data['votarDtos'] = this.votarDtos!.map((v) => v.toJson()).toList();
    }
    data['numeroVotos'] = this.numeroVotos;
    data['porcentajeVotos'] = this.porcentajeVotos;
    return data;
  }
}

class VotarDtos {
  String? idCuestionario;
  GetUsuario? getUsuario;

  VotarDtos({this.idCuestionario, this.getUsuario});

  VotarDtos.fromJson(Map<String, dynamic> json) {
    idCuestionario = json['idCuestionario'];
    getUsuario = json['getUsuario'] != null
        ? new GetUsuario.fromJson(json['getUsuario'])
        : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['idCuestionario'] = this.idCuestionario;
    if (this.getUsuario != null) {
      data['getUsuario'] = this.getUsuario!.toJson();
    }
    return data;
  }
}

class GetUsuario {
  String? id;
  String? username;
  String? name;
  String? lastName;
  String? phoneNumber;
  String? fotoUrl;

  GetUsuario(
      {this.id,
      this.username,
      this.name,
      this.lastName,
      this.phoneNumber,
      this.fotoUrl});

  GetUsuario.fromJson(Map<String, dynamic> json) {
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

class VisualizacionDtos {
  String? idVisualizacion;
  String? idPost;
  String? idUsuario;
  String? foto;
  String? nombreUsuario;
  String? tiempoPublicado;
  String? contenido;
  String? menciones;
  List<ComentariosDtos>? comentariosDtos;
  int? numeroDeMegustas;
  int? numerosDeComentarios;
  UsarioVeFlashPostDto? usarioVeFlashPostDto;

  VisualizacionDtos(
      {this.idVisualizacion,
      this.idPost,
      this.idUsuario,
      this.foto,
      this.nombreUsuario,
      this.tiempoPublicado,
      this.contenido,
      this.menciones,
      this.comentariosDtos,
      this.numeroDeMegustas,
      this.numerosDeComentarios,
      this.usarioVeFlashPostDto});

  VisualizacionDtos.fromJson(Map<String, dynamic> json) {
    idVisualizacion = json['idVisualizacion'];
    idPost = json['idPost'];
    idUsuario = json['idUsuario'];
    foto = json['foto'];
    nombreUsuario = json['nombreUsuario'];
    tiempoPublicado = json['tiempoPublicado'];
    contenido = json['contenido'];
    menciones = json['menciones'];
    comentariosDtos = json['comentariosDtos'];
    numeroDeMegustas = json['numeroDeMegustas'];
    numerosDeComentarios = json['numerosDeComentarios'];
    usarioVeFlashPostDto = json['usarioVeFlashPostDto'] != null
        ? new UsarioVeFlashPostDto.fromJson(json['usarioVeFlashPostDto'])
        : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['idVisualizacion'] = this.idVisualizacion;
    data['idPost'] = this.idPost;
    data['idUsuario'] = this.idUsuario;
    data['foto'] = this.foto;
    data['nombreUsuario'] = this.nombreUsuario;
    data['tiempoPublicado'] = this.tiempoPublicado;
    data['contenido'] = this.contenido;
    data['menciones'] = this.menciones;
    if (this.comentariosDtos != null) {
      data['comentariosDtos'] =
          this.comentariosDtos!.map((v) => v.toJson()).toList();
    }
    data['numeroDeMegustas'] = this.numeroDeMegustas;
    data['numerosDeComentarios'] = this.numerosDeComentarios;
    if (this.usarioVeFlashPostDto != null) {
      data['usarioVeFlashPostDto'] = this.usarioVeFlashPostDto!.toJson();
    }
    return data;
  }
}

class UsarioVeFlashPostDto {
  bool? loHavisto;

  UsarioVeFlashPostDto({this.loHavisto});

  UsarioVeFlashPostDto.fromJson(Map<String, dynamic> json) {
    loHavisto = json['loHavisto'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['loHavisto'] = this.loHavisto;
    return data;
  }
}

class Pueblos {
  String? id;
  String? pueblos;
  Provincia? provincia;

  Pueblos({this.id, this.pueblos, this.provincia});

  Pueblos.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    pueblos = json['pueblos'];
    provincia = json['provincia'] != null
        ? new Provincia.fromJson(json['provincia'])
        : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['pueblos'] = this.pueblos;
    if (this.provincia != null) {
      data['provincia'] = this.provincia!.toJson();
    }
    return data;
  }
}

class Provincia {
  String? id;
  String? provincia;

  Provincia({this.id, this.provincia});

  Provincia.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    provincia = json['provincia'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['provincia'] = this.provincia;
    return data;
  }
}
