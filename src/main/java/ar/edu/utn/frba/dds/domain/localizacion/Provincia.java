package ar.edu.utn.frba.dds.domain.localizacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Provincia {
  String nombre;
  int id;

  public Provincia(String nombre, int id) {
    this.nombre = nombre;
    this.id = id;
  }
}
