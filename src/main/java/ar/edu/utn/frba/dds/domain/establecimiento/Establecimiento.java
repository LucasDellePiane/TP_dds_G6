package ar.edu.utn.frba.dds.domain.establecimiento;

import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import java.util.List;

/*estaciones y sucursales*/
public class Establecimiento {
  private String nombre;
  private Localizacion ubicacionGeografica; //Es Localizacion
  private List<Servicio> servicios;
  private TipoEstablecimiento tipoEstablecimiento;

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

  public List<Servicio> getServicios() {
    return this.servicios;
  }
}
