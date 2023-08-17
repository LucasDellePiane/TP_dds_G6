package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.medioComunicacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.validadores.ValidadorContrasenias;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Usuario {
  private String nombre;
  private String email;
  private String telefono;
  private String contrasenia;
  private String nombreUsuario;
  private Localizacion localizacion;
  private Localizacion localizacion_actual;
  private List<Establecimiento> establecimientosInteres;
  private List<Servicio> serviciosInteres;
  private String apellido;
  private ValidadorContrasenias validador = new ValidadorContrasenias();
  private MedioComunicacion medioComunicacion;

  //Cambiaríamos el rangoHorario por horas directamente
  //private List<RangoHorario> horariosNotificacion;
  private List<Integer> horariosNotificacion;

  public Usuario(String nombreUsuario, String contrasenia) {
    validador.validarContrasenia(nombreUsuario,contrasenia);
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
  }

  public void notificarIncidentes() {
    LocalTime horaActual = LocalTime.now();
    int horaEntero = horaActual.getHour() * 100 + horaActual.getMinute();
    boolean contieneHorarioActual = this.horariosNotificacion.contains(horaEntero);
    if(contieneHorarioActual){
      Comunidad comunidad = (Comunidad) RepositorioComunidad.getInstancia().getComunidades().stream().filter(c -> {
        return c.usuarioEsParte(this);
      });

      List<Incidente> incidentes = comunidad.obtenerIncidentesReportados(this);
      medioComunicacion.notificarIncidentes(this, incidentes);
    }
  }

  public boolean estaInteresado(Servicio servicio) {
    return this.getServiciosInteres().contains(servicio);
  }

  public void notificarIncidente(Incidente incidente) {
    medioComunicacion.notificarIncidente(this, incidente);
  }

  public void agregarHorarioNotificacion(Integer horario){
    horariosNotificacion.add(horario);
  }

}


