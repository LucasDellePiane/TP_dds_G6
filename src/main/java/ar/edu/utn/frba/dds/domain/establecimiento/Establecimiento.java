package ar.edu.utn.frba.dds.domain.establecimiento;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.servicio.EstadoIncidente;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

/*estaciones y sucursales*/
@Getter
@Setter
public class Establecimiento {

  // Atributos

  private String nombre;
  private TipoEstablecimiento tipoEstablecimiento;
  private Localizacion localizacion; //Es Localizacion
  private List<Servicio> servicios = new ArrayList<>();


  // Metodos

  /*
  public void avisarIncidentes(){
  if(!!getservicios().map(servicio->servicio.getIncidentes).isEmpty())  
    avisaALosCercanos(); repoUsuarios.getUsuarios.filter(u.ubicacionAcutla = servicioLocalizacion);
  }
  
  */  
  // Tendriamos que hacer los test
  public void darAltaServicio(Servicio servicioNuevo) {
    this.servicios.add(servicioNuevo);
  }

  public void darBajaServicio(Servicio servicioObsoleto) {
    this.servicios.remove(servicioObsoleto);
  }

  public void agregarIncidente(Incidente incidente){
    // no se le asigna una comunidad al incidente
    // va aca ???
  }

  // con un repo incidentes esto es mas facil xd
  public void estaCerca(Usuario usuario) {

    if(this.localizacion.estaCerca(usuario.getLocalizacion())){

      List<Comunidad> comunidades = RepositorioComunidad.getInstancia()
          .comunidadesALasQuePertenece(usuario);
      List<Servicio> serviciosConIncidentes = this.servicios.stream().filter(servicio -> {
        return !(servicio.getIncidentes().stream().filter(incidente -> {
          return  comunidades.contains(incidente.getComunidad()) && incidente.getEstado().equals(EstadoIncidente.ACTIVO);
        }).collect(Collectors.toList()).isEmpty());
      }).collect(Collectors.toList());

      if(!serviciosConIncidentes.isEmpty()){
        usuario.notificarServiciosCercanos(serviciosConIncidentes);
      }
    }

  }

}
