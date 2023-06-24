package ar.edu.utn.frba.dds.domain.entidad;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*Transportes y organizacion*/
public class Entidad {
  private String nombreEntidad;
  private List<Establecimiento> conjuntoDeEstablecimientos;
  private Localizacion localizacion;

  public List<Servicio> todosLosServicios() {
    return this.getEstablecimientos().stream().
      flatMap(establecimiento -> establecimiento.getServicios().stream()).
      collect(Collectors.toList());
  }
  
  public List<Establecimiento> getEstablecimientos(){
    return this.conjuntoDeEstablecimientos;
  }

  public List<Incidente> incidentesDeEntidad() {
    return this.todosLosServicios().stream().
      flatMap(servicio -> servicio.getIncidentes().stream()).
      collect(Collectors.toList());
  }

  public List<Incidente> incidentesSemanales() {
    return this.incidentesDeEntidad().stream()
      .filter(incidente -> incidente.getHorarioApertura().isAfter(LocalDateTime.now().minusWeeks(1)))
      .collect(Collectors.toList());
  }

  public int cantidadIncidentesEnUnaSemana() {
    return this.incidentesSemanales().size();
  }  
              
  public double promedioDeCierreIncidente() {
    return this.incidentesSemanales().stream()
              .mapToLong(incidente -> incidente.tiempoCierre(incidente)) // Mapear a una lista de enteros
              .average()                                                 // Calcula directamente el promedio, es como hacer sum y dividir por la cant. de elementos
              .orElse(0);                                                // En caso de que no haya elementos devuelve 0
  }
}
