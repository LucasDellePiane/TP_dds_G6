package ar.edu.utn.frba.dds.domain.Ranking;

import java.util.List;

import ar.edu.utn.frba.dds.domain.entidad.Entidad;


public interface Criterio {
    public List<Entidad> calcularRanking(List<Entidad> entidades);
}
