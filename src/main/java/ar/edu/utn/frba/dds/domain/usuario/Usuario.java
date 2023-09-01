package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.medioComunicacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.validadores.ValidadorContrasenias;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Usuario {

  // Atributos

  private String nombre;
  private String apellido;
  private String email;
  private String telefono;

  private List<RangoHorario> horariosNotificacion = new ArrayList<>();
  private MedioComunicacion medioComunicacion;
  private LocalDateTime ultimaHoraNotificacion;

  private String nombreUsuario;
  private String contrasenia;

  private Localizacion localizacion;
  private Localizacion localizacion_actual;

  private List<Establecimiento> establecimientosInteres = new ArrayList<>();
  private List<Servicio> serviciosInteres = new ArrayList<>();

  private ValidadorContrasenias validador = new ValidadorContrasenias();


  // Metodos
  public Usuario(String nombreUsuario, String contrasenia) {
    validador.validarContrasenia(nombreUsuario,contrasenia);
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
  }

  // ============== Notificar incidentes

  private boolean estaEnRangoHorario() {
    LocalTime horaActual = LocalTime.now();
    int horaEntero = horaActual.getHour();
    return this.horariosNotificacion.stream().anyMatch(unRango -> unRango.laHoraPertene(horaEntero));
  }

  public void notificarIncidente(Incidente incidente) {
    if(this.estaEnRangoHorario()) {
      medioComunicacion.notificarIncidente(this, incidente);
      this.ultimaHoraNotificacion = LocalDateTime.now();
    }
  }

  public void notificarIncidentes() { //Pendientes de notificación
    if(this.estaEnRangoHorario()) {
      List<Comunidad> comunidadesUsuario = RepositorioComunidad.getInstancia().getComunidades().stream().filter(unaComunidad -> unaComunidad.usuarioEsParte(this)).collect(Collectors.toList());

      List<Incidente> incidentesAbiertos = comunidadesUsuario.stream()
                                                      .map(unaComunidad -> unaComunidad.incidentesAbiertos())
                                                      .flatMap(Collection::stream)
                                                      .collect(Collectors.toList());
      List<Incidente> incidentesANotificar = incidentesAbiertos.stream()
                                              .filter(unIncidente -> unIncidente.getHorarioApertura().isAfter(this.ultimaHoraNotificacion))
                                              .collect(Collectors.toList());

      incidentesANotificar.stream().forEach(unIncidente -> medioComunicacion.notificarIncidente(this, unIncidente) );
    }
  }

  // ================ Otros

  public void agregarHorarioNotificacion(RangoHorario nuevoRango){
    horariosNotificacion.add(nuevoRango);
  }

  public void notificarServiciosCercanos(List<Servicio> servicios){
    medioComunicacion.notificarServicioCercano(this, servicios);
  }
}


//métodos anteriores POR LAS DUDAS LOS DEJÉ
/*
 private boolean estaEnRangoHorario() {
    LocalTime horaActual = LocalTime.now();
    int horaEntero = horaActual.getHour();
    return !(this.horariosNotificacion.stream().filter(rango -> {
      return rango.getInicio() < horaEntero && rango.getFin() > horaEntero;
    }).collect(Collectors.toList()).isEmpty());
  }

  public void notificarIncidente(Incidente incidente) {
    if(this.estaEnRangoHorario()) {
      medioComunicacion.notificarIncidente(this, incidente);
    }
  }

  // Sugerencia: medioComunicacion tiene que tener un metodo solo que sea notificar, y todo lo q
  // se manda se transforma en string

  public void notificarIncidentes() {
    if(this.estaEnRangoHorario()) {
      Comunidad comunidad = (Comunidad) RepositorioComunidad.getInstancia().getComunidades().stream().filter(c -> {
        return c.usuarioEsParte(this);
      });
      List<Incidente> incidentes = comunidad.obtenerIncidentesDeInteres(this);
      medioComunicacion.notificarIncidentes(this, incidentes);
    }
  }

*/