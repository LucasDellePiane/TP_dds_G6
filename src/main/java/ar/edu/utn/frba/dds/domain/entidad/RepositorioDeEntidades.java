package ar.edu.utn.frba.dds.domain.entidad;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class RepositorioDeEntidades {
    @Getter
    private List<Entidad> entidades = new ArrayList<>();
    private static RepositorioDeEntidades repositorioDeEntidades;
    

  public static RepositorioDeEntidades getInstancia() {
      if (repositorioDeEntidades == null) {
        repositorioDeEntidades = new RepositorioDeEntidades();
      }
      return repositorioDeEntidades;
    }

  public void aniadirEntidad(Entidad entidad){
    this.entidades.add(entidad);
  }
}