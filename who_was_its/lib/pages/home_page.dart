import 'package:flutter/material.dart';
import 'package:who_was_its/componentes/card_post.dart';
import 'package:who_was_its/componentes/postear.dart';
import 'package:who_was_its/componentes/provincias.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String _selectedPuebloId = "";

  void _updateSelectedPueblo(String id) {
    setState(() {
      _selectedPuebloId = id;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home Page'),
        backgroundColor: Colors.black, // Opcional: cambia el color del AppBar
      ),
      body: Container(
        color: Colors.black, // Fondo de la página en negro
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Botón de las provincias
            Container(
              padding: const EdgeInsets.all(8.0),
              // Fondo para separar visualmente
              child:
                  ProvinciasComponent(onPuebloSelected: _updateSelectedPueblo),
            ),
            const SizedBox(height: 8), // Espaciado pequeño
            // Lista de posts
            Expanded(
              child: CardPost(selectedPuebloId: _selectedPuebloId),
            ),
            Expanded(
              child: ElevatedButton(
                  onPressed: () {
                    Navigator.pushReplacement(
                      context,
                      MaterialPageRoute(
                          builder: (context) => PostearComponente(
                                selectedPuebloId: _selectedPuebloId,
                              )),
                    );
                  },
                  child: Text("Flikear")),
            ),
          ],
        ),
      ),
    );
  }
}
