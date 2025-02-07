import 'package:animated_scroll_item/animated_scroll_item.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:who_was_its/models/ver_post.dart';
import 'package:who_was_its/service/api_service.dart';
import 'package:vector_math/vector_math_64.dart' hide Colors;

class CardPost extends StatefulWidget {
  final String selectedPuebloId;

  const CardPost({super.key, required this.selectedPuebloId});

  @override
  State<CardPost> createState() => _CardPostState();
}

class _CardPostState extends State<CardPost> {
  final ApiService _apiService = ApiService();
  List<VerPost> _verPost = [];

  @override
  void didUpdateWidget(covariant CardPost oldWidget) {
    super.didUpdateWidget(oldWidget);
    if (oldWidget.selectedPuebloId != widget.selectedPuebloId) {
      verPost();
    }
  }

  Future<void> verPost() async {
    try {
      final posts = await _apiService.verPosts(widget.selectedPuebloId);
      setState(() {
        _verPost = posts;
      });
    } catch (error) {
      print('No se ha podido ver los post: $error');
    }
  }

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      itemCount: _verPost.length,
      itemBuilder: (context, index) {
        final post = _verPost[index];

        return AnimatedScrollItem(
          configs: [
            ItemAnimationConfig(
              animationRange: const AnimationRange(min: 0, max: .1),
              itemTransform: (
                double animationValue,
                Size size,
                Matrix4 matrix,
              ) {
                return matrix
                  ..scale(animationValue)
                  ..setTranslation(
                      Vector3(size.width * (1 - animationValue) * .5, 0, 0));
              },
              opacityTransform: (animationValue) => animationValue,
            ),
          ],
          size: const Size(double.infinity, 200),
          child: Container(
            margin: const EdgeInsets.symmetric(
                vertical: 8), // Espaciado entre tarjetas
            decoration: BoxDecoration(
              color: Colors.white, // Color de fondo de la tarjeta
              borderRadius: BorderRadius.circular(12),
              border: Border.all(
                color:
                    const Color.fromARGB(255, 224, 64, 251), // Color del borde
                width: 2, // Grosor del borde
              ),
            ),
            child: Padding(
              padding: const EdgeInsets.all(12.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Encabezado con foto, nombre y tiempo en una fila
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Row(
                        children: [
                          // Foto del usuario
                          CircleAvatar(
                            backgroundImage: NetworkImage(post.foto!),
                            radius: 24, // Tamaño de la imagen
                          ),
                          const SizedBox(width: 12),
                          // Nombre del usuario
                          Text(
                            post.nombreUsuario!,
                            style: const TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                            ),
                          ),
                        ],
                      ),
                      // Tiempo publicado
                      Text(
                        post.tiempoPublicado!,
                        style: TextStyle(
                          fontSize: 12,
                          color: Colors.grey[600],
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 16),
                  // Contenido del post (centrado)
                  Expanded(
                    child: Center(
                      child: Text(
                        post.contenido!,
                        style: const TextStyle(
                          fontSize: 14,
                          color: Colors.black87,
                        ),
                        textAlign:
                            TextAlign.center, // Centra el texto horizontalmente
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}
