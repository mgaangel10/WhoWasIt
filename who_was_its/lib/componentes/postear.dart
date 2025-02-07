import 'package:flutter/material.dart';
import 'package:who_was_its/main.dart';
import 'package:who_was_its/models/crear_post_response.dart';
import 'package:who_was_its/models/cuestionario_crear.dart';
import 'package:who_was_its/models/cuestionario_response.dart';
import 'package:who_was_its/models/opciones_crear.dart';
import 'package:who_was_its/models/opciones_response.dart';
import 'package:who_was_its/models/postear.dart';
import 'package:who_was_its/models/ver_opciones.dart';
import 'package:who_was_its/models/ver_post.dart';
import 'package:who_was_its/service/api_service.dart';

class PostearComponente extends StatefulWidget {
  final String selectedPuebloId;
  const PostearComponente({super.key, required this.selectedPuebloId});

  @override
  State<PostearComponente> createState() => _PostearComponenteState();
}

class _PostearComponenteState extends State<PostearComponente> {
  ApiService _api = ApiService();
  final TextEditingController _contentController = TextEditingController();
  final TextEditingController _cuestionarioController = TextEditingController();
  final Postear _p = Postear();
  final CuestionarioCrear _crearCuestionario = CuestionarioCrear();
  CuestionarioResponse _cuestionarioCrear = CuestionarioResponse();
  final OpcionesCrear _opcionesCrear = OpcionesCrear();
  CrearPost _crearPost = CrearPost();
  // Variable para controlar la visibilidad del formulario de creación de cuestionario
  bool _isCuestionarioVisible = false;
  void toggleCuestionarioForm() {
    setState(() {
      _isCuestionarioVisible = !_isCuestionarioVisible;
    });
  }

  Future<void> crearCuestionario() async {
    try {
      _cuestionarioCrear.titulo = _contentController.text;
      final response = await _api.crearCuestionario(_crearCuestionario);
      setState(() {
        _cuestionarioCrear = response;
      });
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Error al crear la cuestionario: $error'),
        ),
      );
    }
  }

  Future<void> crearPost() async {
    try {
      _p.contenido = _contentController.text;

      final response = await _api.crearPost(widget.selectedPuebloId, _p);
      setState(() {
        _crearPost = response;
      });

      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Publicación creada con éxito!'),
        ),
      );

      _contentController.clear();

      // Navegar a MyHomePage después de crear el post
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => MyHomePage()),
      );
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Error al crear la publicación: $e'),
        ),
      );
    }
  }

  void navegarACrearCuestionario() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => CuesTionarioPage()),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Crear Publicación'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            // Campo de texto para la publicación
            TextField(
              controller: _contentController,
              maxLines: null,
              decoration: const InputDecoration(
                hintText: 'Escribe tu publicación...',
                border: OutlineInputBorder(),
              ),
            ),
            const SizedBox(height: 20),

            // Row con los iconos, uno de ellos será el que controle la visibilidad del formulario
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                IconButton(
                  onPressed:
                      toggleCuestionarioForm, // Aquí llamamos a la función que alterna la visibilidad
                  icon: const Icon(Icons.poll),
                ),
                IconButton(
                  onPressed: () {
                    // Acción para el segundo botón (icono)
                  },
                  icon: const Icon(Icons.flash_on_rounded),
                ),
                IconButton(
                  onPressed: () {
                    // Acción para el tercer botón (icono)
                  },
                  icon: const Icon(Icons.article_outlined),
                ),
              ],
            ),
            const SizedBox(height: 20),

            // Si el formulario de cuestionario está visible, mostrar el formulario
            if (_isCuestionarioVisible)
              Padding(
                padding: const EdgeInsets.symmetric(vertical: 16.0),
                child: Column(
                  children: [
                    TextField(
                      controller: _cuestionarioController,
                      decoration: const InputDecoration(
                        hintText: 'Nombre del cuestionario',
                        border: OutlineInputBorder(),
                      ),
                    ),
                    const SizedBox(height: 10),
                    ElevatedButton(
                      onPressed: () {
                        // Lógica para guardar el cuestionario
                        print(
                            "Cuestionario creado: ${_cuestionarioController.text}");
                      },
                      child: const Text('Guardar Cuestionario'),
                    ),
                  ],
                ),
              ),

            const SizedBox(height: 20),

            // Botón para enviar la publicación
            ElevatedButton(
              onPressed: crearPost,
              child: const Text('Enviar'),
            ),
          ],
        ),
      ),
    );
  }
}

class CuesTionarioPage extends StatefulWidget {
  const CuesTionarioPage({super.key});

  @override
  State<CuesTionarioPage> createState() => _CuesTionarioPageState();
}

class _CuesTionarioPageState extends State<CuesTionarioPage> {
  ApiService _api = ApiService();

  final TextEditingController _cuestionarioController = TextEditingController();

  final CuestionarioCrear _crearCuestionario = CuestionarioCrear();
  CuestionarioResponse _cuestionarioCrear = CuestionarioResponse();

  Future<void> crearCuestionario() async {
    try {
      _cuestionarioCrear.titulo = _cuestionarioController.text;
      print(_cuestionarioController);
      final response = await _api.crearCuestionario(_crearCuestionario);
      setState(() {
        _cuestionarioCrear = response;
      });
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) =>
              AgregarOpcionesPage(nombreCuestionario: response.id!),
        ),
      );
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Error al crear la cuestionario: $error'),
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Crear Cuestionario'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _cuestionarioController,
              decoration: const InputDecoration(
                hintText: 'Nombre del cuestionario',
                border: OutlineInputBorder(),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () => crearCuestionario(),
              child: const Text('Crear Cuestionario'),
            ),
          ],
        ),
      ),
    );
  }
}

class AgregarOpcionesPage extends StatefulWidget {
  final String nombreCuestionario;

  const AgregarOpcionesPage({super.key, required this.nombreCuestionario});

  @override
  State<AgregarOpcionesPage> createState() => _AgregarOpcionesPageState();
}

class _AgregarOpcionesPageState extends State<AgregarOpcionesPage> {
  final ApiService _api = ApiService();
  final TextEditingController _opcionesControllers = TextEditingController();
  final OpcionesCrear _opcionesCrear = OpcionesCrear();
  OpcionesResponse _opcionesResponse = OpcionesResponse();
  List<VerOpciones> _verOpciones = [];

  Future<void> crearOpciones() async {
    try {
      _opcionesCrear.opciones = _opcionesControllers.text;
      final response =
          await _api.crearOpciones(widget.nombreCuestionario, _opcionesCrear);
      setState(() {
        _opcionesResponse = response;
      });
      // Después de crear las opciones, actualizar la lista de opciones
      verOpciones();
    } catch (error) {
      // Maneja el error si es necesario
    }
  }

  Future<void> verOpciones() async {
    try {
      final response = await _api.verOpciones(widget.nombreCuestionario);
      setState(() {
        _verOpciones =
            response; // Asegúrate de que este método devuelve una lista válida
      });
    } catch (error) {
      // Maneja el error si es necesario
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Agregar Opciones - ${widget.nombreCuestionario}'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _opcionesControllers,
              decoration: const InputDecoration(
                hintText: 'Nombre de la opción',
                border: OutlineInputBorder(),
              ),
            ),
            // Verifica si _verOpciones está vacío
            Expanded(
              child: _verOpciones.isEmpty
                  ? Center(child: Text('No hay opciones disponibles.'))
                  : ListView.builder(
                      itemCount: _verOpciones.length, // Asegúrate de poner esto
                      itemBuilder: (context, index) {
                        final opciones = _verOpciones[index];
                        return Card(
                          child: Padding(
                            padding: const EdgeInsets.all(8.0),
                            child: Text(opciones.opcion ?? 'Opción sin nombre'),
                          ),
                        );
                      },
                    ),
            ),
            ElevatedButton(
              onPressed: crearOpciones,
              child: const Text('Agregar Opción'),
            ),
            const SizedBox(height: 20),
          ],
        ),
      ),
    );
  }
}
