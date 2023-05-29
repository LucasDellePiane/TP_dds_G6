package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import java.util.List;

public class UsuarioEmpresa extends Usuario {
  List<String> problematicas;
  List<Servicio> serviciosAsociados;

  public UsuarioEmpresa(String nombreUsuario, String contrasenia) {
    super(nombreUsuario, contrasenia);
  }
}
