package ar.edu.utn.frba.dds.domain.Ranking;

import java.util.List;

import ar.edu.utn.frba.dds.domain.entidad.Entidad;


public abstract class Criterio {
    private List<Entidad> ranking;
    public abstract void calcularRanking(List<Entidad> entidades);
}
