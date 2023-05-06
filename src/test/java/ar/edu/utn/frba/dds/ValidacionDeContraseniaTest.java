package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.exceptions.CaracteresRepetidosException;
import ar.edu.utn.frba.dds.exceptions.ContraseniaMuyCortaException;
import ar.edu.utn.frba.dds.exceptions.UsaCrendencialesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ValidacionDeContraseniaTest {

  private final RepositorioDeUsuarios sistema = new RepositorioDeUsuarios();

  @Test
  public void unaContraseniaConCaracteresIgualesNoSirve() throws FileNotFoundException {
    assertThrows(CaracteresRepetidosException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      sistema.validarContrasenia("batman", "muchosaaa");
    });
  }

  @Test
  public void contraseniaInvalidaPorLongitudMenorAOcho() throws FileNotFoundException {
    assertThrows(ContraseniaMuyCortaException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      sistema.validarContrasenia("batman", "abc");
    });
  }

  @Test
  public void contraseniaInvalidaPorUsoDeCredenciales() {
    assertThrows(UsaCrendencialesException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      sistema.validarContrasenia("soybatman", "soybatman");
    });
  }

  @Test
  public void contraseniaValida() {
    sistema.validarContrasenia("soybatman", "elmascapodelmundo");

  }
}


