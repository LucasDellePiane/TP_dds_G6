package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import java.util.List;

public class Comunidad {
  private List<Usuario> miembros;
  private List<Usuario> administradores;
  private List<Servicio> serviciosDeInteres;

  // Metodos

  public void darDeBajaMiembro(Usuario miembro) {
    this.miembros.remove(miembro);
  }

  public void darDeAltaMiembro(Usuario miembroNuevo) {
    this.miembros.add(miembroNuevo);
  }

  public void solicitarServicio(Servicio servicio, Estacion estacion) {
    estacion.darAltaServicio(servicio); // tendria q existir un metodo en estacion que sea controlarSolicitudServicioNuevo o algo asi
  }
}
