package ar.edu.utn.frba.dds.domain.notificacion;

import java.time.LocalDate;
import java.time.LocalTime;

public class RangoHorario {
  LocalTime horarioInicio;
  LocalTime horarioFinal;

  public RangoHorario(LocalTime horarioInicio,LocalTime horarioFinal){
    this.horarioInicio = horarioInicio;
    this.horarioFinal = horarioFinal;
  }
}
