package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEstablecimientos implements WithSimplePersistenceUnit {

  private static final RepositorioEstablecimientos repositorioEstablecimientos = new RepositorioEstablecimientos();

  public static RepositorioEstablecimientos getInstancia() {
    return repositorioEstablecimientos;
  }
  public List<Establecimiento> obtenerTodos() {
    return entityManager().createQuery("from Establecimiento", Establecimiento.class).getResultList();
  }

}
