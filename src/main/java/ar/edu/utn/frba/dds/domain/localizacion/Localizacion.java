package ar.edu.utn.frba.dds.domain.localizacion;

import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacion {
  Provincia provincia;
  Division division;

  public Localizacion(Provincia provincia,Division division){
    this.division = division;
    this.provincia = provincia;
  }

}
