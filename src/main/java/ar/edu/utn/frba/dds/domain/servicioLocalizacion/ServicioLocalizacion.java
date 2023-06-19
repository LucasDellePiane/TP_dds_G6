package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;

public interface ServicioLocalizacion {
  public Division buscarMunicipio(String nombreProvincia, String nombreMunicipio);
  public Division buscarDepartamento(String nombreProvincia, String nombreDepartamento);
  public Provincia buscarProvincia(String nombreProvincia);

}
