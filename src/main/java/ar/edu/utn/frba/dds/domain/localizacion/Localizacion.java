package ar.edu.utn.frba.dds.domain.localizacion;

import ar.edu.utn.frba.dds.domain.AsesorLocalizacion;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import ar.edu.utn.frba.dds.domain.localizacion.division.TipoDivision;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacion {
  Provincia provincia;
  Division division;
  AsesorLocalizacion asesorLocalizacion;

  public Localizacion(Provincia provincia, Division division) {
    this.division = division;
    this.provincia = provincia;
  }

  public boolean localizacionValida() {
    if (division.getTipo().equals(TipoDivision.MUNICIPIO)) {
      asesorLocalizacion.buscarMunicipio(division, provincia.getId());
    }
    if (division.getTipo().equals(TipoDivision.DEPARTAMENTO)) {
      asesorLocalizacion.buscarDepartamento(division, provincia.getId());
    }
    return true;
  }

}


