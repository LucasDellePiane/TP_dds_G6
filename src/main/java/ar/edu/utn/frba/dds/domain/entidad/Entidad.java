package ar.edu.utn.frba.dds.domain.entidad;

import ar.edu.utn.frba.dds.domain.Persistente;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*Transportes y organizacion*/
@Entity
@Table(name = "Entidades")
public class Entidad extends Persistente {
  @Column(name = "nombreEntidad", columnDefinition = "VARCHAR(20)")
  @Getter
  private String nombreEntidad;
  @OneToMany
  @Getter
  private List<Establecimiento> conjuntoDeEstablecimientos = new ArrayList<>();
  @Getter
  @Embedded
  private Localizacion localizacion;

  /*
   * 
   * public avisarEstablecimientoZona(){
   *  this.conjuntDeEstablecimentos.forEach(establecimiento -> establecimiento.avisarIncidentes)
   *
   * }
   * 
   * 
   */
  public void setearNombre(String nombreEntidad) {
    this.nombreEntidad = nombreEntidad;
  }

  public List<Servicio> todosLosServicios() {
    return this.getEstablecimientos().stream().
      flatMap(establecimiento -> establecimiento.getServicios().stream()).
      collect(Collectors.toList());
  }

  public int cantidadServiciosEntidad() {
    return this.todosLosServicios().size();
  }

  public List<Establecimiento> getEstablecimientos(){
    return this.conjuntoDeEstablecimientos;
  }

  public List<Incidente> incidentesDeEntidad() {
    return this.todosLosServicios().stream().
      flatMap(servicio -> servicio.getIncidentes().stream()).
      collect(Collectors.toList());
  }
/*List<Incidente> todosLosIncidentes = servicios.stream()
                .flatMap(servicio -> servicio.getIncidentes().stream())
                .collect(Collectors.toList());*/
  public int cantidadIncidentesEntidad() {
    return this.incidentesDeEntidad().size();
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
    /*return this.incidentesSemanales().stream()
              .mapToLong(incidente -> incidente.tiempoCierre()) // Mapear a una lista de enteros
              .average()                                                // Calcula directamente el promedio, es como hacer sum y dividir por la cant. de elementos
              .orElse(0);         */
    // En caso de que no haya elementos devuelve 0
    if (incidentesSemanales().isEmpty()) {
      return 0;
    }
    float sumaTiempoDeCierre = 0;
    // Recorremos la lista y sumamos los tiempos de cierre
    for (Incidente incidente : incidentesSemanales()) {
      sumaTiempoDeCierre += incidente.tiempoCierre();
    }
    float promedio = sumaTiempoDeCierre / incidentesSemanales().size();
    return promedio;
  }


  public void aniadirEstablecimiento(Establecimiento establecimiento) {
    this.conjuntoDeEstablecimientos.add(establecimiento);
  }
}
