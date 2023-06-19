package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import java.util.List;

public class Empresa {
  String tipo;
  List<String> problematicas;
  List<Servicio> serviciosAsociados;

  public Empresa(String nombreUsuario, String contrasenia, String tipo) {
    super(nombreUsuario, contrasenia);
    this.tipo = tipo;
  }
}



