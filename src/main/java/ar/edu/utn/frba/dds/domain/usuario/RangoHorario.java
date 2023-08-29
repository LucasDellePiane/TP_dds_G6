package ar.edu.utn.frba.dds.domain.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RangoHorario {

  // Atributos

  private int horaInicio;
  private int horaFin;

  // Metodos


  public RangoHorario(int inicio, int fin) {
    this.horaInicio = inicio;
    this.horaFin = fin;
  }

  public boolean laHoraPertene(int hora){
    return hora >= this.horaInicio && hora < this.horaFin;
  }


}
