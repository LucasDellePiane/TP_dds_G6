package ar.edu.utn.frba.dds.testRegistroUsuario;


import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ValidacionRegistroDeEmpresasTest {


  @Test
  public void SiElCSVTiene3EmpresasSeRegistranTodas() {

    RepositorioDeUsuarios repouser = new RepositorioDeUsuarios();

    repouser.registrarEmpresas("entidadesPrestadoras.csv");

    // Verifica que se registraron tres empresas, cantidad hardcodeada del csv
    Assertions.assertEquals(3, repouser.getEmpresasUsuarias().size());
  }


  @Test
  public void LaRutaProporcionadaNoEsCorrectaYLanzaExcepcion() {
    RepositorioDeUsuarios repouser = new RepositorioDeUsuarios();

    // Intentar registrar las empresas y esperar la excepción
    Assertions.assertThrows(RutaInvalidaException.class, () -> {
      repouser.registrarEmpresas("entidades.csv");
    });
  }
}
