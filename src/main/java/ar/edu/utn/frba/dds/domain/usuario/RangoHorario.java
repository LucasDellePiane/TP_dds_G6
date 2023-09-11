package ar.edu.utn.frba.dds.domain.usuario;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "RangosHorarios")
@NoArgsConstructor
public class RangoHorario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_Rango")
  private Integer id_Rango;

  // Atributos
  @Column(name = "horaInicio", columnDefinition = "INTEGER")
  private int horaInicio;
  @Column(name = "horaFin", columnDefinition = "INTEGER")
  private int horaFin;

  // Metodos


  public RangoHorario(int inicio, int fin) {
    this.horaInicio = inicio;
    this.horaFin = fin;
  }

  public boolean laHoraPertene(int hora){
    return hora >= this.horaInicio && hora < this.horaFin;
  }


}
