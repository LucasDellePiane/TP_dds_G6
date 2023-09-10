package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.Persistente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Empresas")
@NoArgsConstructor
public class Empresa extends Persistente {
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



