package ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios;

import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import ar.edu.utn.frba.dds.domain.usuario.Empresa;
import lombok.Getter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeUsuarios {
  @Getter
  private List<Usuario> usuariosDeLaPlataforma = new ArrayList<>();
  private List<Empresa> empresasUsuarias = new ArrayList<>();

  public List<Empresa> getEmpresasUsuarias() {
    return empresasUsuarias;
  }

  public String abrirArchivo(String nombreArchivo) {
    Path path = Paths.get("src", "main", "resources", nombreArchivo);
    String rutaCSV = path.toAbsolutePath().toString();
    return rutaCSV;
  }

  public void aniadirUsuario(Usuario usuario) {
    usuariosDeLaPlataforma.add(usuario);
  }



}
