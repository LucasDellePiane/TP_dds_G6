package ar.edu.utn.frba.dds.domain.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RangoHorario {

  // Atributos

  private int inicio;
  private int fin;

  // Metodos


  public RangoHorario(int inicio, int fin) {
    this.inicio = inicio;
    this.fin = fin;
  }


}
