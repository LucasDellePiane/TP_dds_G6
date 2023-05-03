package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;


public class ValidacionDeContraseniaTest {

  private final SistemaDeRegistroDeUsuario sistema = new SistemaDeRegistroDeUsuario();

  @Test
  public void unaContraseniaConCaracteresIgualesNoSirve() throws FileNotFoundException {
    assertFalse(sistema.validarContrasenia("batman", "muchosaaa"));
  }

  @Test
  public void contraseniaInvalidaPorLongitudMenorAOcho() throws FileNotFoundException {
    assertFalse(sistema.validarContrasenia("Batman", "abc"));
  }

  @Test
  public void contraseniaInvalidaPorUsoDeCredenciales() throws FileNotFoundException {
    assertFalse(sistema.validarContrasenia("soyBatman", "soyBatman"));
  }

  @Test
  public void contraseniaValida() throws FileNotFoundException {
    assertTrue(sistema.validarContrasenia("batman", "supercalifragilisticoespialidoso"));
  }
}


