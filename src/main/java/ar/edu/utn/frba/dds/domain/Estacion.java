package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import java.util.List;

public class Estacion {
  private String nombre;
  private String ubicacion; // que tipo es la ubicacion ??
  private List<Servicio> servicios;

  // Metodos

  // Tendriamos que hacer los test
  public void darAltaServicio(Servicio servicioNuevo){
    this.servicios.add(servicioNuevo);
  }

  public void darBajaServicio(Servicio servicioObsoleto){
    this.servicios.remove(servicioObsoleto);
  }

}
