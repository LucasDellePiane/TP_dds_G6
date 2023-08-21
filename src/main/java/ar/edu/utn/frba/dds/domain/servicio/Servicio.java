package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Servicio {

  // Atributos
  private TipoServicio tipo;
  private boolean fueraDeFuncionamiento;
  private List<Incidente> incidentes;

  // Metodos

  public Servicio(TipoServicio tipo){
    this.tipo = tipo;
    this.incidentes = new ArrayList<>();
  }

  public void informarNoFuncionamiento(String observaciones) {
   this.ComunidadesInteresadasEnElServicio().forEach(comunidad->
   {
    Incidente incidente = new Incidente(observaciones, comunidad);
    incidentes.add(incidente);
    comunidad.reportarIncidente(this, incidente);
    });
  }

  public List<Incidente> obtenerIncidentesAbiertosDeComunidad(Comunidad comunidad){
    return incidentes.stream()
        .filter(incidente -> {
          return incidente.getComunidad().equals(comunidad)
          && incidente.getEstado().equals(EstadoIncidente.ACTIVO);
        })
        .collect(Collectors.toList());
  }
  private List<Comunidad> ComunidadesInteresadasEnElServicio(){
    return RepositorioComunidad.getInstancia().getComunidades().stream().
        filter(comunidad -> comunidad.getServiciosDeInteres().contains(this)).toList();
  }

  
}

// !!! Incidentes repetidos en la lista de incidentes

/*
  fueraDeFuncionamiento: true si esta fuera de funcionamiento, por lo tanto los
  incidentes reportados cuando esta fuera de funcionamiento no hay q guardarlos
*/

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