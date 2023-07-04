package ar.edu.utn.frba.dds.domain.Comunidad;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.notificacion.Email;
import ar.edu.utn.frba.dds.domain.notificacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.notificacion.WhatsApp;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Comunidad {
  private List<Usuario> miembros;
  private List<Usuario> administradores;
  @Getter
  private List<Servicio> serviciosDeInteres;
  @Getter
  private List<Incidente> incidentesReportados = new ArrayList<>(Arrays.asList());
  private List<MedioComunicacion> mediosComunicacion;

  public Comunidad(List<Usuario> miembros, List<Usuario> administradores, List<Servicio> serviciosDeInteres,List<MedioComunicacion> mediosComunicacion) {

    //VERIFICAR
    this.miembros = miembros;
    this.administradores = administradores;
    this.serviciosDeInteres = serviciosDeInteres;
    this.mediosComunicacion = mediosComunicacion;
  }

  // Metodos

  public void darDeBajaMiembro(Usuario miembro) {
    this.miembros.remove(miembro);
  }

  public void darDeAltaMiembro(Usuario miembroNuevo) {
    this.miembros.add(miembroNuevo);
  }

  public void solicitarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.darAltaServicio(servicio); // tendria q existir un metodo en estacion que sea controlarSolicitudServicioNuevo o algo asi
  }


  public boolean usuarioEsParte(Usuario usuario){
    return miembros.contains(usuario); //Tenemos a los administradores en la lista de miembros
  }

  public void reportarIncidente(Servicio servicio, Incidente incidente) {
    incidentesReportados.add(incidente);
    this.notificarIncidente(servicio);
  }

  public void notificarIncidente(Servicio servicioIncidente) {
    //Notificará a todos los miembros que tengan a ese servicio como interés
    List<Usuario> miembrosInteresados = miembros.stream()
                                                .filter(unMiembro -> unMiembro.estaInteresadoServicio(servicioIncidente))
                                                .toList();
    mediosComunicacion.forEach(unMedio -> unMedio.enviarNotifiacion(miembrosInteresados));
  }

}
