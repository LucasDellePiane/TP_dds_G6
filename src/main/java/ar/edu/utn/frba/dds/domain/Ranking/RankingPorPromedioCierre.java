package ar.edu.utn.frba.dds.domain.Ranking;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.utn.frba.dds.domain.entidad.Entidad;

public class RankingPorPromedioCierre extends Criterio{
  List<Entidad> ranking;  
  @Override
  public void calcularRanking(List<Entidad> entidades) {
      Collections.sort(entidades, Comparator.comparingDouble(Entidad::promedioDeCierreIncidente).reversed());
      ranking = entidades;
    }  
}
