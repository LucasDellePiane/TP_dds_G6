package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

public class RepositorioIncidentes implements WithSimplePersistenceUnit {

  @Getter
  private static final RepositorioIncidentes INSTANCE = new RepositorioIncidentes();

  public Incidente buscarPorId(String id) {
    return entityManager().createQuery("from Incidente where id_incidente = :idInc", Incidente.class)
        .setParameter("idInc", id)
        .getResultList()
        .get(0);
  }

}
