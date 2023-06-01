package ar.edu.utn.frba.dds.testRegistroUsuario;


import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.RepositorioDeUsuarios;
import org.junit.jupiter.api.Assertions;

public class ValidacionRegistroDeEmpresas {
  private final RepositorioDeUsuarios repouser = new RepositorioDeUsuarios();

  public void SiElCSVTiene3EmpresasSeRegistranTodas() {
    repouser.registrarEmpresas();
    Assertions.assertEquals();
  }
}
