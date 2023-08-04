package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioIncidentes;

import java.util.ArrayList;
import java.util.List;

public class Servicio {
  private TipoServicio tipo;
  private List<Incidente> incidentes;

  public Servicio(TipoServicio tipo){
    this.tipo = tipo;
    this.incidentes = new ArrayList<>();
  }

  public Incidente informarNoFuncionamiento(String observaciones) {
    //Se crea el incidente, se añade el incidente al servicio y al repoIncidentes, se reporta el incidente en todas las comunidades que tengan a ese servicio como interés
    Incidente incidente = new Incidente(observaciones);
    RepositorioIncidentes.getInstancia().aniadirIncidente(incidente);
    incidentes.add(incidente);
    this.ComunidadesInteresadasEnElServicio().forEach(comunidad-> comunidad.reportarIncidente(incidente));
    return incidente; //Se retorna solo para poder testear
  }

  //ComunidadesInteresadasEnElServicio() DEBERÍA ESTAR ACA O EN EL REPO DE COMUNIDADES ?
  private List<Comunidad> ComunidadesInteresadasEnElServicio(){
    return RepositorioComunidad.getInstancia().getComunidades().stream().
        filter(comunidad -> comunidad.getServiciosDeInteres().contains(this)).toList();
  }

  public List<Incidente> getIncidentes() {
    return this.incidentes;
  }
  
}

