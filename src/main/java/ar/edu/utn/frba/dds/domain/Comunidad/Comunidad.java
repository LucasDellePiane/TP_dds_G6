package ar.edu.utn.frba.dds.domain.Comunidad;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.servicio.EstadoIncidente;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Comunidad {

  // Atributos

  private List<Usuario> miembros;
  private List<Usuario> administradores;

  private List<Servicio> serviciosDeInteres;

  public Comunidad(List<Usuario> miembros, List<Usuario> administradores, List<Servicio> serviciosDeInteres) {
    //VERIFICAR
    this.miembros = miembros;
    this.administradores = administradores;
    this.serviciosDeInteres = serviciosDeInteres;
  }

  public void reportarIncidente(Servicio servicio, Incidente incidente) {
    this.usuariosInteresadosEn(servicio).forEach(usuario -> {
      usuario.notificarIncidente(incidente);
    });
    servicio.registrarIncidente(incidente);
  }

  public List<Incidente> obtenerIncidentesDeInteres(Usuario usuario) {
    return this.getServiciosDeInteres().stream()
        .filter(servicio -> usuario.getServiciosInteres().contains(servicio) )
        .map(servicio -> servicio.obtenerIncidentesAbiertosDeComunidad(this))
        .flatMap(Collection::stream)
        .collect(Collectors.toList());
  }

  public List<Incidente> obtenerIncidentesReportados() {
    return this.getServiciosDeInteres().stream().map(servicio -> {
      return servicio.obtenerIncidentesAbiertosDeComunidad(this);
    }).flatMap(Collection::stream).collect(Collectors.toList());
  }

  public List<Usuario> usuariosInteresadosEn(Servicio servicio) {
    return this.miembros.stream().filter(usuario -> usuario.estaInteresado(servicio))
        .collect(Collectors.toList());
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






// Atributo

//  private List<Incidente> incidentesReportados;


// Metodo

//  public List<Incidente> consultarIncidentesPorEstado(EstadoIncidente estadoIncidente){
//    return this.incidentesReportados.stream()
//                                    .filter(unIncidente -> unIncidente.getEstado().equals(estadoIncidente))
//                                    .collect(Collectors.toList());
//  }


}
