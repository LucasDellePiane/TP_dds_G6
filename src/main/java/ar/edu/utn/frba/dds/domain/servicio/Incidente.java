package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
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
  Comunidad comunidad;
  @Getter
  LocalDateTime horarioApertura;
  @Setter
  @Getter
  LocalDateTime horarioCierre;

  public Incidente(String observaciones, Comunidad comunidad){
    this.observaciones = observaciones;
    this.estado = EstadoIncidente.ACTIVO;
    this.horarioApertura = LocalDateTime.now();
    this.comunidad = comunidad;
  }
  public void cerrarIncidente(){
    this.setHorarioCierre(LocalDateTime.now());
    this.estado = EstadoIncidente.RESUELTO;
  }

  public long tiempoCierre(){
    Duration duracion = Duration.between(this.horarioApertura, this.horarioCierre);
    long cantidadHoras = duracion.toHours();
    return cantidadHoras;
  }

  public boolean esDeLaComunidad(Comunidad comunidad){
    return this.comunidad.equals(comunidad);
  }

  public boolean suEstadoEs(EstadoIncidente estado){
    return this.estado.equals(estado);
  }

  // como el incidente tiene a la comunidad, informa a sus usuarios cercanos.
}
