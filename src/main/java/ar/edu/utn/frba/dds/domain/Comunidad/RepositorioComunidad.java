package ar.edu.utn.frba.dds.domain.Comunidad;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import java.util.List;

public class RepositorioComunidad {
  private List<Comunidad> comunidades;
  private RepositorioComunidad repositorioComunidad;

  private RepositorioComunidad(){

  }

  public RepositorioComunidad getInstancia() {
    if (repositorioComunidad == null) {
      repositorioComunidad = new RepositorioComunidad();
    }
    return repositorioComunidad;
  }

  public List<Comunidad> comunidadesDelUsuario(Usuario usuario){
    return this.comunidades.stream().filter(comunidad -> comunidad.usuarioEsParte(usuario)).toList();
  }
}
