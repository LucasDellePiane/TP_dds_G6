package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.Comunidad.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.notificacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.notificacion.RangoHorario;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
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
  private List<Establecimiento> establecimientosInteres;
  private List<Servicio> serviciosInteres;
  private String apellido;
  private ValidadorContrasenias validador = new ValidadorContrasenias();
  private List<RangoHorario> horariosNotificacion;


  public Usuario(String nombreUsuario, String contrasenia) {
    validador.validarContrasenia(nombreUsuario,contrasenia);
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;

  }


  public void suscribirseMedioComunicacion(MedioComunicacion medioComunicacion){
      medioComunicacion.suscribirUsuario(this);
  }

   // Duda sobre localizacion.

   public Incidente informarNoFuncionamiento(Servicio servicio, String observaciones) {
     Incidente incidente = new Incidente(observaciones);
     servicio.aniadirIncidente(incidente);
     this.reportarIncidente(servicio, incidente);
     return incidente;
   }

    public List<Comunidad> comunidadesDelUsuario(){ //podría estar en repositorioUsuario()
        RepositorioComunidad repositorioComunidad = RepositorioComunidad.getInstancia();
        return repositorioComunidad.getComunidades().stream().filter(unaComunidad -> unaComunidad.usuarioEsParte(this)).toList();
    }

    public void reportarIncidente(Servicio servicio, Incidente incidente){
        List<Comunidad> comunidadesUsuario = this.comunidadesDelUsuario();
        comunidadesUsuario.forEach(unaComunidad -> unaComunidad.reportarIncidente(servicio, incidente));
    }

   public void agregarRango(LocalDate horarioInicio, LocalDate horariofinal){
    RangoHorario rangoHorario = new RangoHorario(horarioInicio,horariofinal);
    horariosNotificacion.add(rangoHorario);
   }

    public boolean estaInteresadoServicio(Servicio servicioInteres){
        return serviciosInteres.contains(servicioInteres);
    }

    public void cerrarIncidente(Incidente incidente) {
        incidente.cerrarIncidente();
    }


}


