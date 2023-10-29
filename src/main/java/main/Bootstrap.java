package main;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.servicio.TipoServicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import com.google.common.util.concurrent.Service;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
/**
 * Ejecutar antes de levantar el servidor por primera vez
 *
 * @author flbulgarelli
 */
public class Bootstrap implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    new Bootstrap().run();
  }

  public void run() {
    withTransaction(() -> {

      Usuario usuarioPrueba = new Usuario("usuario1","estaEsUNCOntra");
      persist(usuarioPrueba);

      Servicio servicio1 = new Servicio(TipoServicio.BAÑO);
      persist(servicio1);
      Servicio servicio2 = new Servicio(TipoServicio.ELEVACION);
      persist(servicio2);
      Servicio servicio3 = new Servicio(TipoServicio.BAÑO);
      persist(servicio3);

      List<Usuario> usuariosMiembros = new ArrayList<>();
      usuariosMiembros.add(usuarioPrueba);
      List<Usuario> usuariosAdmin = new ArrayList<>();
      List<Servicio> servciosDeInteres = new ArrayList<>();
      servciosDeInteres.add(servicio1);
      servciosDeInteres.add(servicio2);
      servciosDeInteres.add(servicio3);

      Comunidad com1 = new Comunidad(usuariosMiembros, usuariosAdmin, servciosDeInteres,"comunidad prueba 1");
      persist(com1);
//      Comunidad com2 = new Comunidad(usuariosMiembros, usuariosAdmin, servciosDeInteres);
//      persist(com2);

      Incidente incidente1Servicio1 = new Incidente("usuario1", com1);
      persist(incidente1Servicio1);
      Incidente incidente2Servicio1 = new Incidente("usuario1", com1);
      persist(incidente2Servicio1);
//      Incidente incidente1Servicio2 = new Incidente("usuario1", com2);
//      Incidente incidente1Servicio3 = new Incidente("usuario1", com2);
//
      servicio1.aniadirIncidente(incidente1Servicio1);
      servicio1.aniadirIncidente(incidente2Servicio1);
//      servicio2.aniadirIncidente(incidente1Servicio2);
//      servicio3.aniadirIncidente(incidente1Servicio3);

    });
  }

}
