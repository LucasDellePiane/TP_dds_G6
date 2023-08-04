package ar.edu.utn.frba.dds.domain.repositorios;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.domain.entidad.Entidad;
import lombok.Getter;

public class RepositorioDeEntidades {

    @Getter
    private List<Entidad> entidades = new ArrayList<>();

    @Getter
    private static RepositorioDeEntidades repositorioDeEntidades = new RepositorioDeEntidades(); // duda
  

  public void aniadirEntidad(Entidad entidad){
    this.entidades.add(entidad);
  }
}