package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.servicio.TipoServicio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Servicio {
  private TipoServicio tipo;
  @Getter
  private List<Incidente> incidentes;

  public Servicio(TipoServicio tipo){
    this.tipo = tipo;
    this.incidentes = new ArrayList<>();
  }

  public void aniadirIncidente(Incidente incidente) {
    incidentes.add(incidente);
  }

  public List<Incidente> getIncidentes() {
    return this.incidentes;
  }
}
