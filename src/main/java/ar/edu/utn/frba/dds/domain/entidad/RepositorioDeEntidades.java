package ar.edu.utn.frba.dds.domain.entidad;

import java.util.ArrayList;
import java.util.List;

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