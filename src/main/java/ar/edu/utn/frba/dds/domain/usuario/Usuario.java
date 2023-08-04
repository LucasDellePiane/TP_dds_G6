package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.notificacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.notificacion.RangoHorario;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.validadores.ValidadorContrasenias;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

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

  public void notificarIncidente() {
    //this.medioComunicacion.notificarIncidente();
  }

  //public void agregarRango(LocalTime horarioInicio, LocalTime horariofinal){
  //    RangoHorario rangoHorario = new RangoHorario(horarioInicio,horariofinal);
  //    horariosNotificacion.add(rangoHorario);
  //}
  public void agregarHorarioNotificacion(Integer horario){
    horariosNotificacion.add(horario);
  }


  //VERIFICAR LÓGICA DE NOFITICACIONES
  //public int proximoHorarioNotificaxion() {
  //  int proximaHora = 1;
  //  return proximaHora;
  //}

}


