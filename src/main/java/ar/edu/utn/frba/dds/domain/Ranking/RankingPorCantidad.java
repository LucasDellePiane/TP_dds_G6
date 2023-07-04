package ar.edu.utn.frba.dds.domain.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.domain.entidad.Entidad;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

import com.opencsv.CSVWriter;


public class RankingPorCantidad implements Criterio{
  String rutaArchivo = "ruta/del/archivo.csv";
  FileWriter fileWriter;
  CSVWriter csvWriter = new CSVWriter(fileWriter);

  public RankingPorCantidad() {
    try{
      this.fileWriter = new FileWriter(rutaArchivo);
    } catch (IOException exception) {
      throw new RutaInvalidaException("No se encontro la ruta");
    }
    
  }


  @Override
  public void calcularRanking(List<Entidad> entidades) {
    Collections.sort(entidades, Comparator.comparingInt(Entidad::cantidadIncidentesEnUnaSemana).reversed());
    generarCSVConNumeracion(entidades.stream().map(Entidad :: getNombreEntidad).toList(), "rankingPorCantidad.csv");
  }
}







