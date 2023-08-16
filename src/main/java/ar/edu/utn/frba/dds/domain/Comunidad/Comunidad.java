package ar.edu.utn.frba.dds.domain.Comunidad;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.servicio.EstadoIncidente;
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


  public Comunidad(List<Usuario> miembros, List<Usuario> administradores, List<Servicio> serviciosDeInteres) {

    //VERIFICAR
    this.miembros = miembros;
    this.administradores = administradores;
    this.serviciosDeInteres = serviciosDeInteres;
  }


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
    return miembros.contains(usuario);
  }

  public void reportarIncidente(Incidente incidente) {
    incidentesReportados.add(incidente);
    this.miembros.stream().forEach(unMiembro -> unMiembro.notificarIncidente(incidente));
  }

  public List<Incidente> consultarIncidentesPorEstado(EstadoIncidente estadoIncidente){
    return this.incidentesReportados.stream()
                                    .filter(unIncidente -> unIncidente.getEstado().equals(estadoIncidente))
                                    .collect(Collectors.toList());
  }


}
