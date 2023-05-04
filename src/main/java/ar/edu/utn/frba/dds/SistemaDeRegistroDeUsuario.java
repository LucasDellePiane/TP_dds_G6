package ar.edu.utn.frba.dds;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class SistemaDeRegistroDeUsuario {

  private List<Usuario> usuariosDeLaPlataforma = new ArrayList<>();

  public void registrarUsuario(String nombreUsuario, String contrasenia) throws FileNotFoundException {
    this.validarContrasenia(nombreUsuario, contrasenia);
    usuariosDeLaPlataforma.add(new Usuario(nombreUsuario, contrasenia));
  }

  public void validarContrasenia(String nombreUsuario, String contrasenia) throws FileNotFoundException {
    this.validarTamanio(contrasenia);
    this.validarConPeoresContrasenias(contrasenia);
    this.validacionCaracteresRepetidos(contrasenia);
    this.validarCredenciales(nombreUsuario,contrasenia);
  }

  private void validacionCaracteresRepetidos(String contrasenia) {
    int len = contrasenia.length();
    for (int i = 0; i < len - 1; i++) {
      if (contrasenia.charAt(i) == contrasenia.charAt(i + 1)) {
        throw new RuntimeException("La contrasenia tiene caracteres repetidos"); // si se encuentra un par de caracteres simultáneos repetidos, el string contiene caracteres simultáneos repetidos
      }
    }
  }

  public void validarConPeoresContrasenias(String contrasenia) throws FileNotFoundException {
    //boolean sinRepetir = true;
    File archivoContraseniasPeligrosas = new File("src\\main\\resources\\vulnerable_passwords.txt");
    Scanner scanner = new Scanner(archivoContraseniasPeligrosas);

    while (scanner.hasNextLine()) {
      String linea = scanner.nextLine();
      if (contrasenia.equals(linea)) {
        throw new RuntimeException("La contrasenia se encuentra entre las peores 10000");
      }
    }
    scanner.close();
  }

  private void validarTamanio(String contrasenia) {
      if(contrasenia.length() <= 8) {
        throw new RuntimeException("La contrasenia es demasiado corta");
      };
  }

  private void validarCredenciales(String nombreUsuario, String contrasenia) {
    if (nombreUsuario.equals(contrasenia)) {
      throw new RuntimeException("La contrasenia utiliza credenciales");
    };
  }

  public List<Usuario> getUsuariosDeLaPlataforma() {
    return usuariosDeLaPlataforma;
  }
}
