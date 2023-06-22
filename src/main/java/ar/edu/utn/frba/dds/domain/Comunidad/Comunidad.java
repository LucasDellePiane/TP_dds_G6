package ar.edu.utn.frba.dds.domain.Comunidad;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import java.util.ArrayList;
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

  public void solicitarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.darAltaServicio(servicio); // tendria q existir un metodo en estacion que sea controlarSolicitudServicioNuevo o algo asi
  }

  public boolean usuarioEsParte(Usuario usuario){
    return miembros.stream().anyMatch(usuario1 -> usuario1.equals(usuario)) ||
    administradores.stream().anyMatch(usuario1 -> usuario1.equals(usuario));
  }
}
