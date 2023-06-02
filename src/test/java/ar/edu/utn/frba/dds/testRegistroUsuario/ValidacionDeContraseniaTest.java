package ar.edu.utn.frba.dds.testRegistroUsuario;

import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.ValidadorPeorContrasenia;
import ar.edu.utn.frba.dds.exceptions.CaracteresRepetidosException;
import ar.edu.utn.frba.dds.exceptions.ContraseniaMuyCortaException;
import ar.edu.utn.frba.dds.exceptions.MalaContraseniaException;
import ar.edu.utn.frba.dds.exceptions.UsaCrendencialesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ValidacionDeContraseniaTest {

  private final RepositorioDeUsuarios repousers = new RepositorioDeUsuarios();


  @Test
  public void unaContraseniaConCaracteresIgualesNoSirve()  {
    assertThrows(CaracteresRepetidosException.class, () -> {
      repousers.validarContrasenia("batman", "muchosaaa");
    });
  }

  @Test
  public void contraseniaInvalidaPorLongitudMenorAOcho() {
    assertThrows(ContraseniaMuyCortaException.class, () -> {
      repousers.validarContrasenia("batman", "abc");
    });
  }

  @Test
  public void contraseniaInvalidaPorUsoDeCredenciales() {
    assertThrows(UsaCrendencialesException.class, () -> {
      repousers.validarContrasenia("soybatman", "soybatman");
    });
  }
  @Test
  public void contraniaEsPeorContrasenia() {
    assertThrows(MalaContraseniaException.class, () -> {
      repousers.validarContrasenia("soybatman", "12345671234567");
    });
  }

  @Test
  public void contraseniaValida() {
    repousers.validarContrasenia("soybatman", "elmascapodelmundo");

  }
}


