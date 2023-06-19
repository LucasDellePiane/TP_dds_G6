package ar.edu.utn.frba.dds.domain.localizacion.division;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Division {

  String nombre;
  TipoDivision tipo;

  public Division(String nombre, TipoDivision tipo) {
    this.nombre = nombre;
    this.tipo = tipo;
  }
}
