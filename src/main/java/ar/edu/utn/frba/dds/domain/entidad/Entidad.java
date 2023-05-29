package ar.edu.utn.frba.dds.domain.entidad;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import java.util.List;

/*Transportes y organizacion*/
public class Entidad {
  private String nombreEntidad;
  private List<Establecimiento> conjuntoDeEstablecimientos;
  private Localizacion localizacion;
}
