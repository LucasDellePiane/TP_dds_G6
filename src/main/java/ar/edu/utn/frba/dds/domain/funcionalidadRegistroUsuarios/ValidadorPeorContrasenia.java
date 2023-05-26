package ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios;

import ar.edu.utn.frba.dds.exceptions.MalaContraseniaException;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;
import lombok.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class ValidadorPeorContrasenia {
  @Getter
  @Setter
  private String rutaPeoresContrasenias; // Esto tendría que estar aca?
  @Getter
  private static ValidadorPeorContrasenia INSTANCE = new ValidadorPeorContrasenia();

  /*private File abrirArchivo() {
    /*
    Una forma de abrirlo para distintos sistemas operativos es la siguiente:
    return new File(getClass().getResource("contraseniasPeligrosas.txt").getPath());
    pero no me funca no se porque, dice que no encuentra el archivo. Esta indexado en el IDE que resources es
    para dejar archivos que se utilizan en estos casos. No se porque tira ese error.

    return new File(this.rutaPeoresContrasenias);

  }*/

  public void validarPosiblePeorContrasenia(String contrasenia) {
    try{
      //File archivoContraseniasPeligrosas = this.abrirArchivo();

      String resourcePath = "contraseniasPeligrosas.txt";
      Path path = Paths.get("src", "main", "resources", resourcePath);
      String absolutePath = path.toAbsolutePath().toString();

      BufferedReader br = new BufferedReader(
          new InputStreamReader(new FileInputStream(absolutePath),
              StandardCharsets.UTF_8));

      String linea;
      while ((linea = br.readLine()) != null) {
        if (contrasenia.contains(linea)) { // cambie el equals por contains
          throw new MalaContraseniaException("La contrasenia se encuentra entre las peores 10000");
        }
      }
      br.close();
    }catch (IOException e){
      throw new RutaInvalidaException("No se encontro el archivo en la ruta indicada");
    }

  }

}
