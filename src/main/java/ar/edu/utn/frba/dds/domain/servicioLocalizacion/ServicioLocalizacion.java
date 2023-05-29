package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import java.io.IOException;

public interface ServicioLocalizacion {
  public ListadoMuncipios listadoDeMunicipiosDeProvincia(int idProvincia);
  public ListadoDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia);
  public ListadoProvincias listadoDeProvincias();
}
