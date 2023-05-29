package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ListadoDepartamentos{
  public int cantidad;
  public int total;
  public int inicio;
  public List<Division> departamentos;

  public Division buscar(String nombreDepartamento){
    for(Division departamento : this.departamentos){
      if (departamento.getNombre().equals(nombreDepartamento)) {
        return departamento;
      }
    }
    return null;
  }
}
