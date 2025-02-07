import 'package:flutter/material.dart';
import 'package:who_was_its/main.dart';
import 'package:who_was_its/models/crear_usuario_anonimo.dart';
import 'package:who_was_its/service/api_service.dart';
import 'package:who_was_its/pages/home_page.dart';

class UsuarioAnonimoPage extends StatefulWidget {
  const UsuarioAnonimoPage({super.key});

  @override
  State<UsuarioAnonimoPage> createState() => _UsuarioAnonimoPageState();
}

class _UsuarioAnonimoPageState extends State<UsuarioAnonimoPage> {
  final _formKey = GlobalKey<FormState>();
  final ApiService _apiService = ApiService();
  final CrearUsuarioAnonimo _usuarioAnonimoDto = CrearUsuarioAnonimo();

  String? _selectedAvatarUrl;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [
              const Color.fromARGB(255, 0, 0, 0),
              const Color.fromARGB(255, 0, 0, 0)
            ],
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
          ),
        ),
        child: Center(
          child: Padding(
            padding: const EdgeInsets.all(24.0),
            child: Form(
              key: _formKey,
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  Text(
                    'Crear Usuario Anónimo',
                    style: TextStyle(
                      fontSize: 28,
                      fontWeight: FontWeight.bold,
                      color: const Color.fromARGB(255, 224, 64, 251),
                    ),
                  ),
                  SizedBox(height: 20),
                  TextFormField(
                    decoration: InputDecoration(
                      labelText: 'Nombre de Usuario',
                      prefixIcon: Icon(Icons.person),
                      filled: true,
                      fillColor: const Color(0xFFFFFFFF),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(30),
                        borderSide: BorderSide.none,
                      ),
                    ),
                    onSaved: (value) {
                      _usuarioAnonimoDto.nombreUsuario = value;
                    },
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Por favor ingresa un nombre de usuario';
                      }
                      return null;
                    },
                  ),
                  SizedBox(height: 20),
                  GestureDetector(
                    onTap: () async {
                      await _showAvatarSelectionModal();
                    },
                    child: Container(
                      padding: EdgeInsets.symmetric(vertical: 10),
                      decoration: BoxDecoration(
                        color: Colors.white,
                        borderRadius: BorderRadius.circular(30),
                      ),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 16),
                            child: Text(
                              _selectedAvatarUrl != null
                                  ? 'Avatar seleccionado'
                                  : 'Seleccionar un avatar',
                              style: TextStyle(color: Colors.grey.shade700),
                            ),
                          ),
                          if (_selectedAvatarUrl != null)
                            Padding(
                              padding: const EdgeInsets.only(right: 16),
                              child: CircleAvatar(
                                backgroundImage:
                                    NetworkImage(_selectedAvatarUrl!),
                              ),
                            ),
                        ],
                      ),
                    ),
                  ),
                  SizedBox(height: 20),
                  TextFormField(
                    decoration: InputDecoration(
                      labelText: 'Conocido Como',
                      prefixIcon: Icon(Icons.approval),
                      filled: true,
                      fillColor: Colors.white,
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(30),
                        borderSide: BorderSide.none,
                      ),
                    ),
                    onSaved: (value) {
                      _usuarioAnonimoDto.conocidoComo = value;
                    },
                    validator: (value) {
                      if (value == null || value.isEmpty) {
                        return 'Por favor ingresa un apodo o conocido como';
                      }
                      return null;
                    },
                  ),
                  SizedBox(height: 30),
                  ElevatedButton(
                    onPressed: () {
                      if (_formKey.currentState!.validate() &&
                          _selectedAvatarUrl != null) {
                        _formKey.currentState!.save();
                        _usuarioAnonimoDto.foto = _selectedAvatarUrl;
                        _apiService
                            .crearUsuarioAnonimo(_usuarioAnonimoDto)
                            .then((response) {
                          Navigator.pushReplacement(
                            context,
                            MaterialPageRoute(
                                builder: (context) => MyHomePage()),
                          );

                          ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                            content:
                                Text('Usuario anónimo creado exitosamente'),
                          ));
                        }).catchError((error) {
                          print('Error: $error');
                          ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                            content: Text('Error: $error'),
                          ));
                        });
                      } else {
                        ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                          content: Text('Por favor selecciona un avatar'),
                        ));
                      }
                    },
                    style: ElevatedButton.styleFrom(
                      padding:
                          EdgeInsets.symmetric(horizontal: 50, vertical: 15),
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(30),
                      ),
                      backgroundColor: const Color.fromARGB(255, 224, 64, 251),
                    ),
                    child: Text(
                      'Crear Usuario',
                      style: TextStyle(fontSize: 18),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  Future<void> _showAvatarSelectionModal() async {
    final avatars = await _apiService.verAvatares();

    showModalBottomSheet(
      context: context,
      builder: (context) {
        return ListView.builder(
          itemCount: avatars.length,
          itemBuilder: (context, index) {
            final avatar = avatars[index];
            return ListTile(
              leading: CircleAvatar(
                backgroundImage: NetworkImage(avatar.imagen!),
              ),
              title: Text("Avatar"),
              onTap: () {
                setState(() {
                  _selectedAvatarUrl = avatar.imagen!;
                });
                Navigator.pop(context);
              },
            );
          },
        );
      },
    );
  }
}
