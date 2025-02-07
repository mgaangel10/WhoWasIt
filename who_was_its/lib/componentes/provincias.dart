import 'package:dropdown_button2/dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:who_was_its/models/provicnias_response.dart';
import 'package:who_was_its/service/api_service.dart';

class ProvinciasComponent extends StatefulWidget {
  final ValueChanged<String> onPuebloSelected;

  const ProvinciasComponent({super.key, required this.onPuebloSelected});

  @override
  State<ProvinciasComponent> createState() => _ProvinciasComponentState();
}

class _ProvinciasComponentState extends State<ProvinciasComponent> {
  final ApiService _api = ApiService();
  List<ProvinciasResponse> _provincias = [];
  String? _selectedAvatarUrl;

  @override
  void initState() {
    super.initState();
    listarProvincias();
    cargarIdProvincia();
  }

  Future<void> listarProvincias() async {
    try {
      final response = await _api.verProvicnias();
      setState(() {
        _provincias = response;
      });
    } catch (e) {
      print("Error al obtener provincias: ${e.toString()}");
    }
  }

  Future<void> cargarIdProvincia() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    _selectedAvatarUrl = prefs.getString("idP");
  }

  @override
  Widget build(BuildContext context) {
    return _provincias.isEmpty
        ? const Center(child: CircularProgressIndicator())
        : SingleChildScrollView(
            scrollDirection: Axis.horizontal,
            child: Row(
              children: _provincias.map((provincia) {
                return Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 8.0),
                  child: DropdownButtonHideUnderline(
                    child: DropdownButton2(
                      isExpanded: true,
                      customButton: Container(
                        padding: const EdgeInsets.symmetric(
                            horizontal: 16, vertical: 8),
                        decoration: BoxDecoration(
                          color: Colors.blue,
                          borderRadius: BorderRadius.circular(8),
                        ),
                        child: Text(
                          provincia.provincia ?? 'Sin nombre',
                          style: const TextStyle(
                              color: Colors.white, fontWeight: FontWeight.bold),
                        ),
                      ),
                      items: (provincia.puebloDtos ?? [])
                          .map((pueblo) => DropdownMenuItem<String>(
                                value: pueblo.idPueblo,
                                child: Text(
                                  pueblo.nombrePueblo ?? 'Sin nombre',
                                  style: const TextStyle(fontSize: 14),
                                ),
                              ))
                          .toList(),
                      onChanged: (value) async {
                        _selectedAvatarUrl = value;
                        SharedPreferences prefs =
                            await SharedPreferences.getInstance();
                        await prefs.setString("idP", _selectedAvatarUrl!);

                        // Notifica el cambio al widget padre
                        widget.onPuebloSelected(_selectedAvatarUrl!);

                        print("Pueblo seleccionado: $value");
                      },
                    ),
                  ),
                );
              }).toList(),
            ),
          );
  }
}
