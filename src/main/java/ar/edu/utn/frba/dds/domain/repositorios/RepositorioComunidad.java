package ar.edu.utn.frba.dds.domain.repositorios;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioComunidad implements WithSimplePersistenceUnit {
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

//  public List<Comunidad> comunidadesALasQuePertenece(Usuario usuario){
//    return this.getComunidades().stream().filter(c -> {
//      return c.usuarioEsParte(usuario);
//    }).collect(Collectors.toList());
//  }

  public List<Comunidad> obtenerTodos() {
    return entityManager().createQuery("from Comunidad ", Comunidad.class).getResultList();
  }

  public List<Comunidad> comunidadesALasQuePertenece(Usuario usuario) { // creo que esto es asi
    String id_usuario = usuario.getId_usuario().toString();
    return entityManager().createQuery("from Comunidad c Join Usuario u on :id_usuario", Comunidad.class)
        .setParameter("id_usuario", "%" + id_usuario + "%")
        .getResultList();
  }

}
