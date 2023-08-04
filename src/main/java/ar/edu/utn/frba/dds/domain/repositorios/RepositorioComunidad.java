package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioComunidad {
  @Getter
  private List<Comunidad> comunidades;
  private static RepositorioComunidad repositorioComunidad;

  public RepositorioComunidad() {
    List<Comunidad> comunidades = new ArrayList<>(Arrays.asList());
    this.comunidades = comunidades;
  }

  public static RepositorioComunidad getInstancia() {
    if (repositorioComunidad == null) {
      repositorioComunidad = new RepositorioComunidad();
    }
    return repositorioComunidad;
  }

  //DEBERÍA ESTAR ACA O EN SERVICIO ?
  //public List<Comunidad> ComunidadesInteresadasEnElServicio(Servicio servicio) {
  //return this.comunidades.stream().filter(comunidad -> comunidad.getServiciosDeInteres().contains(servicio)).toList();
  //}

  public void aniadirComunidad(Comunidad comunidad) {
    comunidades.add(comunidad);
  }

}
