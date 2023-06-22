package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.notificacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.notificacion.RangoHorario;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacion;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacionGeoRefApi;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Usuario {
  private String nombre;
  private String email;
  private String contrasenia;
  private String nombreUsuario;
  private Localizacion localizacion;
  private List<Establecimiento> establecimientosInteres;
  private List<Servicio> serviciosInteres;
  private String apellido;
  private ValidadorContrasenias validador = new ValidadorContrasenias();
  private List<RangoHorario> horariosNotificacion;
  private MedioComunicacion medioComunicacion;

  public Usuario(String nombreUsuario, String contrasenia) {
    validador.validarContrasenia(nombreUsuario,contrasenia);
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;

  }

   // Duda sobre localizacion.
   public void informarNoFuncionamiento(Servicio servicio, String observaciones) {
     Incidente incidente = new Incidente(servicio, observaciones);
     incidente.notificarInicio(this);
   }

   public void agregarRango(LocalDate horarioInicio, LocalDate horariofinal){
    RangoHorario rangoHorario = new RangoHorario(horarioInicio,horariofinal);
    horariosNotificacion.add(rangoHorario);
   }


}


