package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioIncidentes {
  @Getter
  private List<Incidente> incidentes;
  private static RepositorioIncidentes repositorioIncidentes;

  public RepositorioIncidentes() {
    List<Incidente> incidentes = new ArrayList<>(Arrays.asList());
    this.incidentes = incidentes;
  }

  public void aniadirIncidente(Incidente incidente) {
    incidentes.add(incidente);
  }

  public static RepositorioIncidentes getInstancia() {
    if (repositorioIncidentes == null) {
      repositorioIncidentes = new RepositorioIncidentes();
    }
    return repositorioIncidentes;
  }

}
