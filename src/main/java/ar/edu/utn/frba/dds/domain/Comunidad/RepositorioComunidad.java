package ar.edu.utn.frba.dds.domain.Comunidad;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;

import java.util.List;

public class RepositorioComunidad {
  @Getter
  private List<Comunidad> comunidades;
  private static RepositorioComunidad repositorioComunidad;

  private RepositorioComunidad() {
    // Aquí puedes realizar la inicialización de la instancia
  }

  public static RepositorioComunidad getInstancia() {
    if (repositorioComunidad == null) {
      repositorioComunidad = new RepositorioComunidad();
    }
    return repositorioComunidad;
  }

}
