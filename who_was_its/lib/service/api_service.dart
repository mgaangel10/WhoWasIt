import 'package:who_was_its/models/avatares.dart';
import 'package:who_was_its/models/crear_post_response.dart';
import 'package:who_was_its/models/crear_usuario_anonimo.dart';
import 'package:who_was_its/models/cuestionario_crear.dart';
import 'package:who_was_its/models/cuestionario_response.dart';
import 'package:who_was_its/models/login.dart';
import 'package:who_was_its/models/login_response.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import 'package:who_was_its/models/opciones_crear.dart';
import 'package:who_was_its/models/opciones_response.dart';
import 'package:who_was_its/models/postear.dart';
import 'package:who_was_its/models/provicnias_response.dart';
import 'package:who_was_its/models/usuario_anonimo_response.dart';
import 'package:who_was_its/models/ver_opciones.dart';

import 'dart:convert';
import 'dart:ffi';

import 'package:who_was_its/models/ver_post.dart';

class ApiService {
  final String baseUrl = 'http://10.0.2.2:9000';

  //ver Opciones
  Future<List<VerOpciones>> verOpciones(String id) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");

    final response = await http.get(
      Uri.parse('http://10.0.2.2:9000/usuario/ver/opciones/${id}'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
    );

    if (response.statusCode == 200) {
      final responseBody = json.decode(response.body) as List;
      final content =
          responseBody.map((json) => VerOpciones.fromJson(json)).toList();
      return content;
    } else {
      throw Exception('Failed to load opciones');
    }
  }

  //CREAR OPCIONES
  Future<OpcionesResponse> crearOpciones(
      String idCuestionario, OpcionesCrear loginDto) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");
    final response = await http.post(
      Uri.parse(
          'http://10.0.2.2:9000/usuario/crear/opciones/${idCuestionario}'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
      body: jsonEncode(loginDto.toJson()),
    );
    if (response.statusCode == 201) {
      OpcionesResponse loginResponse =
          OpcionesResponse.fromJson(jsonDecode(response.body));

      return loginResponse;
    } else {
      throw Exception('error al crear post');
    }
  }

  //CUESTIONARIO
  Future<CuestionarioResponse> crearCuestionario(
      CuestionarioCrear loginDto) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");
    final response = await http.post(
      Uri.parse('http://10.0.2.2:9000/usuario/crear/cuestionario'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
      body: jsonEncode(loginDto.toJson()),
    );
    if (response.statusCode == 201) {
      CuestionarioResponse loginResponse =
          CuestionarioResponse.fromJson(jsonDecode(response.body));

      return loginResponse;
    } else {
      throw Exception('error al crear post');
    }
  }

  //PROVINCIAS
  Future<List<ProvinciasResponse>> verProvicnias() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");

    final response = await http.get(
      Uri.parse('http://10.0.2.2:9000/usuario/todas/provincias'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
    );

    if (response.statusCode == 200) {
      final responseBody = json.decode(response.body) as List;
      final content = responseBody
          .map((json) => ProvinciasResponse.fromJson(json))
          .toList();
      return content;
    } else {
      throw Exception('Failed to load provincias');
    }
  }

  //CREAR POST
  Future<CrearPost> crearPost(String id, Postear loginDto) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");
    final response = await http.post(
      Uri.parse('http://10.0.2.2:9000/usuario/nuevo/post/${id}'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
      body: jsonEncode(loginDto.toJson()),
    );
    if (response.statusCode == 201) {
      CrearPost loginResponse = CrearPost.fromJson(jsonDecode(response.body));

      return loginResponse;
    } else {
      throw Exception('error al crear post');
    }
  }

  //VER AVATARES
  Future<List<Avatares>> verAvatares() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");

    final response = await http.get(
      Uri.parse('http://10.0.2.2:9000/usuario/todos/avatares'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
    );

    if (response.statusCode == 200) {
      final responseBody = json.decode(response.body) as List;
      final content =
          responseBody.map((json) => Avatares.fromJson(json)).toList();
      return content;
    } else {
      throw Exception('Failed to load avatares');
    }
  }

  //CREAR USUARIO ANONIMO
  Future<UsuarioAnonimoResponse> crearUsuarioAnonimo(
      CrearUsuarioAnonimo loginDto) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");
    final response = await http.post(
      Uri.parse('http://10.0.2.2:9000/usuario/crear/usuario/anonimo'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
      body: jsonEncode(loginDto.toJson()),
    );
    if (response.statusCode == 201) {
      UsuarioAnonimoResponse loginResponse =
          UsuarioAnonimoResponse.fromJson(jsonDecode(response.body));

      return loginResponse;
    } else {
      throw Exception('error al crear usuario anonimo');
    }
  }

  //VER POST
  Future<List<VerPost>> verPosts(String id) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    String? token = prefs.getString("token");

    final response = await http.get(
      Uri.parse('http://10.0.2.2:9000/usuario/filtrar/post/${id}'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
        'Authorization': 'Bearer $token'
      },
    );

    if (response.statusCode == 200) {
      final responseBody = json.decode(response.body) as List;
      final content =
          responseBody.map((json) => VerPost.fromJson(json)).toList();
      return content;
    } else {
      throw Exception('Failed to load comentarios');
    }
  }

  //LOGIN
  Future<LoginResponse> login(LoginDto loginDto) async {
    final response = await http.post(
      Uri.parse('http://10.0.2.2:9000/auth/login/user'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
        'accept': 'application/json',
      },
      body: loginDto.toJson(),
    );
    if (response.statusCode == 201) {
      LoginResponse loginResponse =
          LoginResponse.fromJson(jsonDecode(response.body));
      String? token = loginResponse.token;
      String? id = loginResponse.id;
      String? nombreUsuario = loginResponse.email;
      SharedPreferences prefs = await SharedPreferences.getInstance();
      await prefs.setString('token', token!);
      await prefs.setString('userId', id!);
      await prefs.setString('nombre', nombreUsuario!);

      return loginResponse;
    } else {
      throw Exception('El email y/o contraseña no son correctos');
    }
  }
}
