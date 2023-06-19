package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.exceptions.CaracteresRepetidosException;
import ar.edu.utn.frba.dds.exceptions.ContraseniaMuyCortaException;
import ar.edu.utn.frba.dds.exceptions.UsaCrendencialesException;

public class ValidadorContrasenias {

  public ValidadorContrasenias(){}

  public static ValidadorContrasenias INSTANCE = new ValidadorContrasenias();

  public void validarContrasenia(String nombreUsuario, String contrasenia) {

    this.validarTamanio(contrasenia);
    this.validacionCaracteresRepetidos(contrasenia);
    this.validarCredenciales(nombreUsuario, contrasenia);
    this.validarConPeoresContrasenias(contrasenia);
  }

  private void validacionCaracteresRepetidos(String contrasenia) {
    int len = contrasenia.length();
    for (int i = 0; i < len - 1; i++) {
      if (contrasenia.charAt(i) == contrasenia.charAt(i + 1)) {
        throw new CaracteresRepetidosException("La contrasenia tiene caracteres repetidos");
        // si se encuentra un par de caracteres simultáneos repetidos,
        // el string contiene caracteres simultáneos repetidos
      }
    }
  }

  public void validarConPeoresContrasenias(String contrasenia) {
    // ValidadorPeorContrasenia.getINSTANCE().setRutaPeoresContrasenias("src\\main\\resources\\contraseniasPeligrosas.txt");
    ValidadorPeorContrasenia.getINSTANCE().validarPosiblePeorContrasenia(contrasenia);
  }

  private void validarTamanio(String contrasenia) {
    if (contrasenia.length() <= 8) {
      throw new ContraseniaMuyCortaException("La contrasenia es demasiado corta");
    }
  }

  private void validarCredenciales(String nombreUsuario, String contrasenia) {
    if (nombreUsuario.equals(contrasenia)) {
      throw new UsaCrendencialesException("La contrasenia utiliza credenciales");
    }
  }



}
