package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import java.util.List;

public class Empresa {
  String nombreEmpresa;
  TipoEmpresa tipo;
  List<String> problematicas;
  List<Servicio> serviciosAsociados;

  public Empresa(String nombreEmpresa, TipoEmpresa tipo) {
    this.nombreEmpresa = nombreEmpresa;
    this.tipo = tipo;
  }
}



