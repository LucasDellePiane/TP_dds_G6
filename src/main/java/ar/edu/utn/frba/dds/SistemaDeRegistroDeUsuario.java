package ar.edu.utn.frba.dds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SistemaDeRegistroDeUsuario {
  /*
  //private List<Usuario> usuariosDeLaPlataforma = new ArrayList<>();
  //private List<Usuario> usuariosDeLaPlataforma;

  public void registrarUsuario(String nombreUsuario, String contrasenia)
      throws FileNotFoundException {
    this.validarContrasenia(nombreUsuario, contrasenia);
    usuariosDeLaPlataforma.add(new Usuario(nombreUsuario, contrasenia));
  }
  */

  public void validarContrasenia(String nombreUsuario, String contrasenia)
      throws IOException {
    this.validarTamanio(contrasenia);
    this.validarConPeoresContrasenias(contrasenia);
    this.validacionCaracteresRepetidos(contrasenia);
    this.validarCredenciales(nombreUsuario, contrasenia);
  }

  private void validacionCaracteresRepetidos(String contrasenia) {
    int len = contrasenia.length();
    for (int i = 0; i < len - 1; i++) {
      if (contrasenia.charAt(i) == contrasenia.charAt(i + 1)) {
        throw new RuntimeException("La contrasenia tiene caracteres repetidos");
        // si se encuentra un par de caracteres simultáneos repetidos,
        // el string contiene caracteres simultáneos repetidos
      }
    }
  }

  public void validarConPeoresContrasenias(String contrasenia) throws IOException {
    File archivoContraseniasPeligrosas =
        new File("src\\main\\recursos\\contraseniasPeligrosas.txt");

    BufferedReader br = new BufferedReader(
        new InputStreamReader(new FileInputStream(archivoContraseniasPeligrosas),
            StandardCharsets.UTF_8));

    String linea;
    while ((linea = br.readLine()) != null) {
      if (contrasenia.equals(linea)) {
        throw new RuntimeException("La contrasenia se encuentra entre las peores 10000");
      }
    }
    br.close();
  }

  private void validarTamanio(String contrasenia) {
    if (contrasenia.length() <= 8) {
      throw new RuntimeException("La contrasenia es demasiado corta");
    }
  }

  private void validarCredenciales(String nombreUsuario, String contrasenia) {
    if (nombreUsuario.equals(contrasenia)) {
      throw new RuntimeException("La contrasenia utiliza credenciales");
    }
  }

  /*
  public List<Usuario> getUsuariosDeLaPlataforma() {
    return usuariosDeLaPlataforma;
  }
   */
}
