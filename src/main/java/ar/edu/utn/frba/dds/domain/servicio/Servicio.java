package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioIncidentes;

import java.util.ArrayList;
import java.util.List;

public class Servicio {
  private TipoServicio tipo;
  private List<Incidente> incidentes; // PONEMOS UN REPO INCIDENTES

  public Servicio(TipoServicio tipo){
    this.tipo = tipo;
    this.incidentes = new ArrayList<>();
  }


  // REVISAR EL HECHO DE QUE HAY INCIDENTES REPETIDOS EN LA LISTA DE INCIDENTES DEL SERVICIO
  public void informarNoFuncionamiento(String observaciones) {
   this.ComunidadesInteresadasEnElServicio().forEach(comunidad->
   {
    Incidente incidente = new Incidente(observaciones, comunidad);
    incidentes.add(incidente);
    comunidad.reportarIncidente(incidente);
    });
  }

  //NO BORRAR - ESPERANDO APROBACIÓN DE ROLI
  //public void informarNoFuncionamiento(String observaciones) {
  //  Incidente incidente = new Incidente(observaciones);
  //  incidentes.add(incidente);
  //  this.ComunidadesInteresadasEnElServicio().forEach(comunidad->
  //  {
  //    IncidenteComunidad incidenteComunidad = new IncidenteComunidad(incidente);
  //    comunidad.reportarIncidente(incidenteComunidad);
  //  });
  //}


  //ComunidadesInteresadasEnElServicio() DEBERÍA ESTAR ACA O EN EL REPO DE COMUNIDADES ?
  private List<Comunidad> ComunidadesInteresadasEnElServicio(){
    return RepositorioComunidad.getInstancia().getComunidades().stream().
        filter(comunidad -> comunidad.getServiciosDeInteres().contains(this)).toList();
  }

  public List<Incidente> getIncidentes() {
    return this.incidentes;
  }
  
}

