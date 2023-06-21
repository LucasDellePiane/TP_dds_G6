package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;

public interface ServicioLocalizacion {
  public Localizacion buscarMunicipio(String nombreProvincia, String nombreMunicipio);
  public Localizacion buscarDepartamento(String nombreProvincia, String nombreDepartamento);
  public Localizacion buscarProvincia(String nombreProvincia);
}
