package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.Comunidad.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Setter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Incidente {
  Servicio servicio;
  String observaciones;
  EstadoIncidente estado;
  LocalDateTime horarioApertura;
  @Setter
  LocalDateTime horarioCierre;

  public Incidente(Servicio servicio, String observaciones){
    this.servicio = servicio;
    this.observaciones = observaciones;
    this.estado = EstadoIncidente.ACTIVO;
    this.horarioApertura = LocalDateTime.now();
  }
  public void cerrarIncidente(Incidente incidente){
    incidente.setHorarioCierre(LocalDateTime.now());
  }

  public void notificarInicio(Usuario usuario, RepositorioComunidad repositorioComunidad){
     repositorioComunidad.getInstancia();
  }

  public long tiempoCierre(Incidente incidente){
    Duration duracion = Duration.between(incidente.horarioCierre,incidente.horarioApertura);
    long cantidadHoras = duracion.toHours();
    return cantidadHoras;
  }
}
