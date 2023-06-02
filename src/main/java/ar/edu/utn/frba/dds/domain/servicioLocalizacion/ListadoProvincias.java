package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import lombok.Getter;
import lombok.Setter;
import java.nio.file.ProviderNotFoundException;
import java.util.List;
@Getter
@Setter
public class ListadoProvincias {
  public int cantidad;
  public int total;
  public int inicio;
  public List<Provincia> provincias;

  public ListadoProvincias(int cantidad, int total, int inicio, List<Provincia> provincias) {
    this.cantidad = cantidad;
    this.total = total;
    this.inicio = inicio;
    this.provincias = provincias;
  }

  public Provincia buscar(String nombre){
    for(Provincia provincia : this.provincias){
      if (provincia.getNombre().equals(nombre)) {
        return provincia;
      }
    }
    return null;
  }
}
