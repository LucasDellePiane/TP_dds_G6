package ar.edu.utn.frba.dds;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistroDeUsuario {

  private ArrayList<String> t_password = new ArrayList<>();

  public void registrarUsuario(String usuario, String contrasenia) {
    this.validarContrasenia(contrasenia);
  }


  public void validarContrasenia(String contrasenia) {

  }

  private void validacionCaracteresRepetidos(String contrasenia){
    for (int i = 0; i < contrasenia.length()-1; i++) {
      char caracter = contrasenia.charAt(i);
      char otroCaracter = contrasenia.charAt(i+1);
        if (caracter == otroCaracter) {
        //inserte excepcion
        break;
      }
    }
  }

  private void crearListaContraseñas() throws FileNotFoundException {
    File archivoContraseniasPeligrosas = new File("ruta");
    Scanner scanner = new Scanner(archivoContraseniasPeligrosas);

    while (scanner.hasNextLine()) {
      String linea = scanner.nextLine();
      lineasArchivo.add(linea);
     }
    
    scanner.close();
  }
  
  private boolean contraseniaSegura(String contrasenia) { //Chequear nombre
    return !lineasArchivo.contains(contrasenia);
  }

  private boolean validarTamanio(String contrasenia) {
      if (contrasenia.length() >= 8  && contrasenia.length() < 64) {
        return true;
      }else{
        //execpcion
      }
  }
  
}
