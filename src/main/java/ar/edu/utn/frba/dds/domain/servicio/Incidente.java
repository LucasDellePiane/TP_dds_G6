package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.Comunidad.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import ar.edu.utn.frba.dds.domain.Comunidad.RepositorioComunidad;

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

  /* */

}
