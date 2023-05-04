package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;


public class ValidacionDeContraseniaTest {

  private final SistemaDeRegistroDeUsuario sistema = new SistemaDeRegistroDeUsuario();

  @Test
  public void unaContraseniaConCaracteresIgualesNoSirve() throws FileNotFoundException {
    assertThrows(RuntimeException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      sistema.validarContrasenia("batman", "muchosaaa");
    });
  }

  @Test
  public void contraseniaInvalidaPorLongitudMenorAOcho() throws FileNotFoundException {
    assertThrows(RuntimeException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      sistema.validarContrasenia("batman", "abc");
    });
  }

  @Test
  public void contraseniaInvalidaPorUsoDeCredenciales() throws FileNotFoundException {
    assertThrows(RuntimeException.class, () -> {
      // Aquí debes llamar a tu método que lanza la excepción
      // Por ejemplo, si tu método se llama lanzarExcepcion(), puedes hacer:
      sistema.validarContrasenia("soybatman", "soybatman");
    });
  }

  @Test
  public void contraseniaValida() throws FileNotFoundException {
    sistema.validarContrasenia("soybatman", "elmascapodelmundo");
  }
}


