package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.entidad.Entidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEstablecimientos implements WithSimplePersistenceUnit {
  @Getter
  private List<Establecimiento> establecimientos = new ArrayList<>();
  private static RepositorioEstablecimientos repositorioEstablecimientos;

  public static RepositorioEstablecimientos getInstancia() {
    if(repositorioEstablecimientos == null){
      repositorioEstablecimientos = new RepositorioEstablecimientos();
    }
    return repositorioEstablecimientos;
  }

  public void aniadirEstablecimiento(Establecimiento establecimiento){
    this.establecimientos.add(establecimiento);
  }
  public List<Establecimiento> obtenerTodos() {
    return entityManager().createQuery("from Establecimiento", Establecimiento.class).getResultList();
  }


  public Establecimiento buscar(Integer id) {
    return entityManager().createQuery("from Establecimiento where id_establecimiento = :idEst", Establecimiento.class)
        .setParameter("idEst", id)
        .getResultList()
        .get(0);
  }

}
