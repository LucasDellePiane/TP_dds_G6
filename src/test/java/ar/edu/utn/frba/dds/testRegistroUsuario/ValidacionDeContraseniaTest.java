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

  @BeforeEach
  public void comienzo(){
    String ruta = "src\\main\\resources\\contraseniasPeligrosas.txt";
    ValidadorPeorContrasenia.getINSTANCE().setRutaPeoresContrasenias(ruta);
  }

  @Test
  public void unaContraseniaConCaracteresIgualesNoSirve()  {
    assertThrows(CaracteresRepetidosException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      repousers.validarContrasenia("batman", "muchosaaa");
    });
  }

  @Test
  public void contraseniaInvalidaPorLongitudMenorAOcho() {
    assertThrows(ContraseniaMuyCortaException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      repousers.validarContrasenia("batman", "abc");
    });
  }

  @Test
  public void contraseniaInvalidaPorUsoDeCredenciales() {
    assertThrows(UsaCrendencialesException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
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


