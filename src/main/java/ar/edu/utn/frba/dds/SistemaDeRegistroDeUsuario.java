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
    if(this.validarContrasenia(nombreUsuario, contrasenia)) {
      // guardar usuario y contrasenia
      usuariosDeLaPlataforma.add(new Usuario(nombreUsuario, contrasenia));
    }
    else {
      throw new RuntimeException("La contrasenia no es segura");
      //tirar excepcion de contra invalida
    }
  }


  public boolean validarContrasenia(String nombreUsuario, String contrasenia) throws FileNotFoundException {
    return this.validarTamanio(contrasenia) && this.validarConPeoresContrasenias(contrasenia) &&
        this.validacionCaracteresRepetidos(contrasenia) && validarCredenciales(nombreUsuario,contrasenia);
  }
/*
  private boolean validacionCaracteresRepetidos(String contrasenia){
    boolean tieneCaracterRepetido = false;
    for (int i = 0; i < contrasenia.length()-1; i++) {
      char caracter = contrasenia.charAt(i);
      char otroCaracter = contrasenia.charAt(i+1);
        if (caracter == otroCaracter) {
          tieneCaracterRepetido = true;
      }
        i++;
    }
    return tieneCaracterRepetido;
  }
*/

  private boolean validacionCaracteresRepetidos(String contrasenia) {
    int len = contrasenia.length();
    for (int i = 0; i < len - 1; i++) {
      if (contrasenia.charAt(i) == contrasenia.charAt(i + 1)) {
        return false; // si se encuentra un par de caracteres simultáneos repetidos, el string contiene caracteres simultáneos repetidos
      }
    }
    return true;
  }

  public boolean validarConPeoresContrasenias(String contrasenia) throws FileNotFoundException {
    boolean sinRepetir = true;
    File archivoContraseniasPeligrosas = new File("src\\main\\resources\\vulnerable_passwords.txt");
    Scanner scanner = new Scanner(archivoContraseniasPeligrosas);

    while (scanner.hasNextLine()) {
      String linea = scanner.nextLine();
      if (contrasenia.equals(linea)) {
        sinRepetir = false;
      }
    }
    scanner.close();
    return sinRepetir;
  }




  private boolean validarTamanio(String contrasenia) {
      return (contrasenia.length() >= 8  && contrasenia.length() <= 64);
  }

  private boolean validarCredenciales(String nombreUsuario, String contrasenia) {
    return !nombreUsuario.equals(contrasenia);
  }
  
}
