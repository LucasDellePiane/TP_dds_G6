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
  private List<RangoHorario> horariosNotificacion;
  private MedioComunicacion medioComunicacion;

  public Usuario(String nombreUsuario, String contrasenia) {
    validador.validarContrasenia(nombreUsuario,contrasenia);
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
  }

  public void agregarRango(LocalTime horarioInicio, LocalTime horariofinal){
      RangoHorario rangoHorario = new RangoHorario(horarioInicio,horariofinal);
      horariosNotificacion.add(rangoHorario);
  }

  // Esto no se convierte en un pasamanos ???
  public void notificarIncidente() {
      //this.medioComunicacion.notificarIncidente();
  }


  public int proximoHorarioNotificaxion() { //FALTA ESTO !!!, ALTERNATIVA: UTILIZAR ENUMS PARA LOS RANGOS HORARIOS
    int proximaHora = 1;
    return proximaHora;
  }


    // comunidadesDelUsuario() ESTA FUNCION NO SE UTILIZARIA MÁS
    //public List<Comunidad> comunidadesDelUsuario(){ //podría estar en repositorioUsuario()
    //    RepositorioComunidad repositorioComunidad = RepositorioComunidad.getInstancia();
    //    return repositorioComunidad.getComunidades().stream().filter(unaComunidad -> unaComunidad.usuarioEsParte(this)).toList();
    //}

}


