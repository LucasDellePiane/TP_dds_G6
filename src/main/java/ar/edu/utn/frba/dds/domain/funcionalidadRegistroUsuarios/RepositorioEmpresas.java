package ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios;

import ar.edu.utn.frba.dds.domain.usuario.Empresa;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;

public class RepositorioEmpresas {

  public void registrarEmpresas(String nombreArchivo) {
    try (CSVParser parser = new CSVParser(new FileReader(this.abrirArchivo(nombreArchivo)), CSVFormat.DEFAULT)) {
      for (CSVRecord record : parser) {
        String nombreEmpresa = record.get(0);
        String contrasenia = record.get(1);
        String tipo = record.get(3);

        this.registrarEmpresa(nombreEmpresa, contrasenia, tipo);
      }
    } catch (IOException e) {
      throw new RutaInvalidaException("La ruta es invalida");
    }
  }

  public void registrarEmpresa(String nombreEmpresa, String contrasenia, String tipo) {
    this.validarContrasenia(nombreEmpresa, contrasenia);
    empresasUsuarias.add(new Empresa(nombreEmpresa, contrasenia, tipo));
  }

}
