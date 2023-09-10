package ar.edu.utn.frba.dds.domain.localizacion.division;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Division {

  String nombre;
  TipoDivision tipo;

  public Division(String nombre, TipoDivision tipo) {
    this.nombre = nombre;
    this.tipo = tipo;
  }
}
