package ar.edu.utn.frba.dds.testRegistroUsuario;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.ValidadorPeorContrasenia;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;
import org.junit.jupiter.api.Test;

public class ValidacionArchivoPeoresContrasenias {

  private final RepositorioDeUsuarios repouser = new RepositorioDeUsuarios();

  @Test
  public void unaRutaIncorrectaGeneraExcepcion() {
    assertThrows(RutaInvalidaException.class, () -> {
      String ruta = "src\\main\\recursos\\contraseniasPeligrosas.txt";
      ValidadorPeorContrasenia.getINSTANCE().setRutaPeoresContrasenias(ruta);
      repouser.validarContrasenia("soybatman", "peroprefierosersuperman");
    });}

  @Test
  public void unaRutaCorrectaNoGeneraExcepcion() {
    try{
      String ruta = "src\\main\\resources\\contraseniasPeligrosas.txt";
      ValidadorPeorContrasenia.getINSTANCE().setRutaPeoresContrasenias(ruta);
      repouser.validarContrasenia("soybatman", "elmascapodelmundo");
    }catch (Exception e){
      throw new RuntimeException("El metodo lanzó una excepcion inesperada");
    }}
}
