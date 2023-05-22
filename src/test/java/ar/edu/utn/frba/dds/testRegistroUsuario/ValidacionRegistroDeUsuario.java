package ar.edu.utn.frba.dds.testRegistroUsuario;

import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.ValidadorPeorContrasenia;
import org.junit.jupiter.api.*;

public class ValidacionRegistroDeUsuario {

  private final RepositorioDeUsuarios repousers = new RepositorioDeUsuarios();

  @BeforeEach
  public void comienzo(){
    String ruta = "src\\main\\resources\\contraseniasPeligrosas.txt";
    ValidadorPeorContrasenia.getINSTANCE().setRutaPeoresContrasenias(ruta);
  }
  @Test
  public void unUsuarioSeRegistraSiSeValidaCorrectamenteLaContrasenia() {
    repousers.registrarUsuario("soybatman", "elmascapodelmundo");
    Assertions.assertTrue(repousers.getUsuariosDeLaPlataforma().stream().anyMatch(usuario -> usuario.getNombreUsuario().equals("soybatman")  && usuario.getContrasenia().equals("elmascapodelmundo")));
}

  @Test // Esta forma es la correcta? --> Preguntar a Rolli
  public void unUsuarioNoSeRegistraSiNoSeValidaCorrectamenteLaContrasenia() {
    try{
      repousers.registrarUsuario("soybatman", "123456789");
    }catch (Exception e){
      Assertions.assertFalse(repousers.getUsuariosDeLaPlataforma().stream().anyMatch(usuario -> usuario.getNombreUsuario().equals("soybatman") && usuario.getContrasenia().equals("123456789")));
    }
  }

}
