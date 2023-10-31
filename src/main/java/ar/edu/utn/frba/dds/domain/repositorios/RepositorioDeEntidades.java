package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.Ranking.Criterio;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.File;
import java.util.stream.Collectors;
import ar.edu.utn.frba.dds.domain.entidad.Entidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import lombok.Getter;

public class RepositorioDeEntidades {

  @Getter
  private List<Entidad> entidades = new ArrayList<>();

  @Getter
  private static RepositorioDeEntidades repositorioDeEntidades;

  private List<Criterio> criterios = new ArrayList<>();

  public static RepositorioDeEntidades getInstancia() {
    if (repositorioDeEntidades == null) {
      repositorioDeEntidades = new RepositorioDeEntidades();
    }
    return repositorioDeEntidades;
  }

  public void aniadirEntidad(Entidad entidad){
    this.entidades.add(entidad);
  }

  public void aniadirCriterio(Criterio criterio) {
    this.criterios.add(criterio);
  }

  public void generarRankings(){

    this.eliminarRankingsAntiguos();
    criterios.forEach(criterio -> criterio.calcularRanking(this.entidades));
  }

  public List<Establecimiento> obtenerEstablecimientos(){
   return this.entidades.stream().flatMap(entidad -> entidad.getEstablecimientos().stream()).toList();
  }

  public List<String> calcularRankingCantidad() {
    List<Entidad> entidadesDelRanking = this.entidades;
    entidadesDelRanking.sort(Comparator.comparingInt(Entidad::cantidadIncidentesEnUnaSemana).reversed());
    int numeroMinimo = Math.min(entidades.size(), 9);
    return entidadesDelRanking.subList(0, numeroMinimo).stream().map(Entidad::getNombreEntidad).toList();
  }

  public List<String> calcularRankingCierre() {
    List<Entidad> entidadesDelRanking = this.entidades;
    entidadesDelRanking.sort(Comparator.comparingDouble(Entidad::promedioDeCierreIncidente).reversed());
    int numeroMinimo = Math.min(entidades.size(), 9);
    return entidadesDelRanking.subList(0, numeroMinimo).stream().map(Entidad::getNombreEntidad).toList();
  }

  public void eliminarRankingsAntiguos() {
    String directorio = "RankingsCSV";
    File directorioAEliminar = new File(directorio);
    // Verifica si el directorio existe
    if (directorioAEliminar.exists() && directorioAEliminar.isDirectory()) {
      // Obtiene la lista de archivos en el directorio
      File[] archivos = directorioAEliminar.listFiles();
      if (archivos != null) {
        for (File archivo : archivos) {
          if (archivo.isFile()) {
            archivo.delete();
          }
        }
      }
    }
  }
}
