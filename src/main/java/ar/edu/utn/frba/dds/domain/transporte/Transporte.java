package ar.edu.utn.frba.dds.domain.transporte;

import ar.edu.utn.frba.dds.domain.Estacion;
import ar.edu.utn.frba.dds.domain.transporte.TipoTransporte;
import java.util.List;

public class Transporte {
  private String nombreLinea;
  // Esto no estaría mejor si ponemos la estacion origen en la pos 0 y la final en la ultima?
  private Estacion estacionOrigen;
  private Estacion estacionDestino;
  private List<Estacion> conjuntoDeEstaciones;
  private TipoTransporte tipo;


}
