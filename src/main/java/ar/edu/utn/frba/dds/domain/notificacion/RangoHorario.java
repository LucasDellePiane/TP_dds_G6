package ar.edu.utn.frba.dds.domain.notificacion;

import java.time.LocalDate;

public class RangoHorario {
  LocalDate horarioInicio;
  LocalDate horarioFinal;

  public RangoHorario(LocalDate horarioInicio,LocalDate horarioFinal){
    this.horarioInicio = horarioInicio;
    this.horarioFinal = horarioFinal;
  }
}
