package ar.edu.utn.frba.dds.domain.localizacion;

import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacionGeoRefApi;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacion {
  String provincia;
  Division division;

  public Localizacion(String provincia, Division division) {
    this.division = division;
    this.provincia = provincia;
  }



}


