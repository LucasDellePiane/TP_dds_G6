package ar.edu.utn.frba.dds.domain.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.edu.utn.frba.dds.domain.entidad.Entidad;


public class RankingPorCantidad implements Criterio{
  private List<Entidad> rankingSemanal;

  public RankingPorCantidad() {
    this.rankingSemanal = new ArrayList<>();
  }

  @Override
  public List<Entidad> calcularRanking(List<Entidad> entidades) {
    Collections.sort(entidades, Comparator.comparingInt(Entidad::cantidadIncidentesEnUnaSemana).reversed());
    return entidades;
  }
}




