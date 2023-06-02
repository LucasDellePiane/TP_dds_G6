package ar.edu.utn.frba.dds.domain.localizacion.division;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Division {

  String nombre;
  int id;
  TipoDivision tipo;

  public Division(String nombre, int id, TipoDivision tipo) {
    this.nombre = nombre;
    this.id = id;
    this.tipo = tipo;
  }
}
