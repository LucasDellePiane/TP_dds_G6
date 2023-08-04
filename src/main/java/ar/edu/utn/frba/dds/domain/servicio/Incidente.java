package ar.edu.utn.frba.dds.domain.servicio;

import lombok.Getter;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDateTime;

public class Incidente {
  @Getter
  String observaciones;
  @Getter
  EstadoIncidente estado;
  @Getter
  LocalDateTime horarioApertura;
  @Setter
  LocalDateTime horarioCierre;

  public Incidente(String observaciones){
    this.observaciones = observaciones;
    this.estado = EstadoIncidente.ACTIVO;
    this.horarioApertura = LocalDateTime.now();
  }
  public void cerrarIncidente(){
    this.setHorarioCierre(LocalDateTime.now());
    this.estado = EstadoIncidente.RESUELTO;
  }

  public long tiempoCierre(Incidente incidente){
    Duration duracion = Duration.between(incidente.horarioCierre,incidente.horarioApertura);
    long cantidadHoras = duracion.toHours();
    return cantidadHoras;
  }


}
