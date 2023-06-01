package ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import ar.edu.utn.frba.dds.domain.usuario.UsuarioEmpresa;
import ar.edu.utn.frba.dds.exceptions.CaracteresRepetidosException;
import ar.edu.utn.frba.dds.exceptions.ContraseniaMuyCortaException;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;
import ar.edu.utn.frba.dds.exceptions.UsaCrendencialesException;
import lombok.Getter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class RepositorioDeUsuarios {
  @Getter
  private List<Usuario> usuariosDeLaPlataforma = new ArrayList<>();
  private List<UsuarioEmpresa> empresasUsuarias = new ArrayList<>();

  public void registrarEmpresas() {
    String resourcePath = "entidadesPrestadoras.csv";
    Path path = Paths.get("src", "main", "resources", resourcePath);
    String archivoCSV = path.toAbsolutePath().toString();

    try (CSVParser parser = new CSVParser(new FileReader(archivoCSV), CSVFormat.newFormat(';'))) {
      for (CSVRecord record : parser) {
        String nombreEmpresa = record.get("NombreUsuario");
        String contrasenia = record.get("Contrasenia");
        String tipo = record.get("Tipo");

        this.registrarEmpresa(nombreEmpresa, contrasenia, tipo);
      }
    } catch (IOException e) {
      throw new RutaInvalidaException("La ruta indicada no existe");
    }
  }

  public void registrarEmpresa(String nombreEmpresa, String contrasenia, String tipo) {
    this.validarContrasenia(nombreEmpresa, contrasenia);
    empresasUsuarias.add(new UsuarioEmpresa(nombreEmpresa, contrasenia, tipo));
  }


  public void registrarUsuario(String nombreUsuario, String contrasenia) {
    this.validarContrasenia(nombreUsuario, contrasenia);
    usuariosDeLaPlataforma.add(new Usuario(nombreUsuario, contrasenia));
  }


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

  /*
  public List<Usuario> getUsuariosDeLaPlataforma() {
    return usuariosDeLaPlataforma;
  }
   */
}
