package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ListadoMuncipios {
  public int cantidad;
  public int total;
  public int inicio;
  public List<Division> municipios;

  public Division buscar(String nombre){
    for(Division municipio : this.municipios){
      if (municipio.getNombre().equals(nombre)) {
        return municipio;
      }
    }
    return null;
  }
}

