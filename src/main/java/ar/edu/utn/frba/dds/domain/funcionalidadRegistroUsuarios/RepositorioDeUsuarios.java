package ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeUsuarios {
  @Getter
  private List<Usuario> usuariosDeLaPlataforma = new ArrayList<>();

  @Getter
  private static RepositorioDeUsuarios INSTANCE = new RepositorioDeUsuarios();
  public void aniadirUsuario(Usuario usuario) {
    usuariosDeLaPlataforma.add(usuario);
  }



}
