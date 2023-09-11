package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Empresas")
@NoArgsConstructor
public class Empresa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_empresa")
  private Integer id_empresa;
  @Column(name = "nombreEmpresa", columnDefinition = "VARCHAR(20)")
  String nombreEmpresa;
  @Enumerated
  @Column(name = "tipoEmpresa")
  TipoEmpresa tipo;
  @ElementCollection
  List<String> problematicas;
  @ElementCollection
  List<Servicio> serviciosAsociados;

  public Empresa(String nombreEmpresa, TipoEmpresa tipo) {
    this.nombreEmpresa = nombreEmpresa;
    this.tipo = tipo;
  }
}



