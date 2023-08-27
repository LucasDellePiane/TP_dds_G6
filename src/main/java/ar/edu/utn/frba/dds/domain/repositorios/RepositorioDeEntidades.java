package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.Ranking.Criterio;
import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.domain.entidad.Entidad;
import lombok.Getter;

public class RepositorioDeEntidades {

  @Getter
  private List<Entidad> entidades = new ArrayList<>();

  @Getter
  private static RepositorioDeEntidades repositorioDeEntidades = new RepositorioDeEntidades(); // duda

  private List<Criterio> criterios = new ArrayList<>();

  public void aniadirEntidad(Entidad entidad){
    this.entidades.add(entidad);
  }

  public void aniadirCriterio(Criterio criterio) {
    this.criterios.add(criterio);
  }

  public void generarRankings(){
    criterios.forEach(criterio -> criterio.calcularRanking(this.entidades));
  }
}