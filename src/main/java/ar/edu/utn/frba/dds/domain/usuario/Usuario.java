package ar.edu.utn.frba.dds.domain.usuario;

import static ar.edu.utn.frba.dds.domain.servicio.EstadoIncidente.ACTIVO;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.medioComunicacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.validadores.ValidadorContrasenias;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
@NoArgsConstructor
public class Usuario{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Integer id_usuario;
  // Atributos
  @Column(name = "apellido", columnDefinition = "VARCHAR(20)")
  private String apellido;
  @Column(name = "name", columnDefinition = "VARCHAR(20)", insertable = false, updatable = false)
  private String name;
  @Column(name = "email", columnDefinition = "VARCHAR(40)")
  private String email;
  @Column(name = "telefono", columnDefinition = "VARCHAR(10)")
  private String telefono;

  @OneToMany
  @JoinColumn(name = "id_Rango")
  private List<RangoHorario> horariosNotificacion = new ArrayList<>();
  @Transient
  private MedioComunicacion medioComunicacion;
  @Column(name = "ultimaHoraNotificacion", columnDefinition = "DATE")
  private LocalDateTime ultimaHoraNotificacion;

  @Column(name = "user_first_name", columnDefinition = "VARCHAR(20)")
  private String user_first_name;
  @Column(name = "contrasenia", columnDefinition = "VARCHAR(20)")
  private String contrasenia;
  @Column(name = "localizacion")
  @Embedded
  private Localizacion localizacion;
  @Column(name = "localizacion_actual")
  @Embedded
  private Localizacion localizacion_actual;
  @ManyToMany
  @JoinTable(
      name = "usuario_establecimiento", // Nombre de la tabla intermedia
      joinColumns = @JoinColumn(name = "id_usuario"), // Columna que hace referencia a esta entidad
      inverseJoinColumns = @JoinColumn(name = "id_establecimiento") // Columna que hace referencia a la otra entidad
  )
  private List<Establecimiento> establecimientosInteres = new ArrayList<>();
  @ManyToMany
  @JoinTable(
      name = "usuario_servicio", // Nombre de la tabla intermedia
      joinColumns = @JoinColumn(name = "id_usuario"), // Columna que hace referencia a esta entidad
      inverseJoinColumns = @JoinColumn(name = "id_servicio") // Columna que hace referencia a la otra entidad
  )
  private List<Servicio> serviciosInteres = new ArrayList<>();
  @Transient
  private ValidadorContrasenias validador = new ValidadorContrasenias();

  /*Agrego*/
  @ManyToMany
  @JoinTable(
      name = "usuario_comunidad_miembro", // Nombre de la tabla intermedia
      joinColumns = @JoinColumn(name = "id_usuario"), // Columna que hace referencia a esta entidad
      inverseJoinColumns = @JoinColumn(name = "id_comunidad") // Columna que hace referencia a la otra entidad
  )
  private List<Comunidad> comunidadMiembro;

  @ManyToMany
  @JoinTable(
      name = "usuario_comunidad_administrador", // Nombre de la tabla intermedia
      joinColumns = @JoinColumn(name = "id_usuario"), // Columna que hace referencia a esta entidad
      inverseJoinColumns = @JoinColumn(name = "id_comunidad") // Columna que hace referencia a la otra entidad
  )
  private List<Comunidad> comunidadAdministrador;

  // Metodos
  public Usuario(String user_first_name, String contrasenia) {
    validador.validarContrasenia(user_first_name,contrasenia);
    this.user_first_name = user_first_name;
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
                                                      .map(unaComunidad -> unaComunidad.consultarIncidentesPorEstado(ACTIVO))
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