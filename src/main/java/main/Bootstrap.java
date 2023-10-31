package main;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.entidad.Entidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeEntidades;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.servicio.TipoServicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import com.google.common.util.concurrent.Service;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

      Usuario usuarioPrueba = new Usuario("usuario1","elmascapodelmundo");
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


      //---------------PRUEBAS RANKINGS-----------------------//
      Entidad entidad1 = new Entidad();
      entidad1.setearNombre("SuperHeroes");
      Entidad entidad2 = new Entidad();
      entidad2.setearNombre("SuperVillanos");
      Establecimiento establecimientoDeLa1 = new Establecimiento();
      Establecimiento establecimientoDeLa2 = new Establecimiento();
      Servicio servicioDeLa1 = new Servicio(TipoServicio.BAÑO);
      Servicio servicioDeLa2 = new Servicio(TipoServicio.BAÑO);
      Usuario luki = new Usuario("usuario1", "elmascapodelmundo");
      Usuario lucho = new Usuario("usuario2", "elmascapodelmundo");
      List<Usuario> miembros = new ArrayList<>(Arrays.asList(luki, lucho));
      List<Usuario> administradores = new ArrayList<>(Arrays.asList(luki));
      List<Servicio> servicios = new ArrayList<>(Arrays.asList(servicioDeLa1, servicioDeLa2));
      Comunidad rockandrolleros =new Comunidad(miembros, administradores, servicios,"RockAndRolleros");


      RepositorioComunidad.getInstancia().aniadirComunidad(rockandrolleros);

      RepositorioDeEntidades.getInstancia().aniadirEntidad(entidad1);
      RepositorioDeEntidades.getInstancia().aniadirEntidad(entidad2);

      entidad1.aniadirEstablecimiento(establecimientoDeLa1);
      entidad2.aniadirEstablecimiento(establecimientoDeLa2);
      establecimientoDeLa1.darAltaServicio(servicioDeLa1);
      establecimientoDeLa2.darAltaServicio(servicioDeLa2);

      /*servicioDeLa1.informarNoFuncionamiento("Se tapo el baño");
      servicioDeLa1.informarNoFuncionamiento("No hay agua");
      servicioDeLa2.informarNoFuncionamiento("No anda la cadena");*/

      Incidente incidenteDeLa1 = new Incidente("Se tapo el baño", rockandrolleros);
      Incidente incidenteDeLa2 = new Incidente("No hay agua", rockandrolleros);
      Incidente incidente2DeLa1 = new Incidente("No anda la cadena", rockandrolleros);

      //ya se que esta mal pero queria mirar rapido si funcionaba
      servicioDeLa1.aniadirIncidente(incidenteDeLa1);
      servicioDeLa1.aniadirIncidente(incidente2DeLa1);
      servicioDeLa2.aniadirIncidente(incidenteDeLa2);

      servicioDeLa1.getIncidentes().get(0).cerrarIncidente();
      servicioDeLa1.getIncidentes().get(1).cerrarIncidente();
      servicioDeLa2.getIncidentes().get(0).cerrarIncidente();

      servicioDeLa1.getIncidentes().get(0).setHorarioCierre(LocalDateTime.now().plusHours(5));
      servicioDeLa1.getIncidentes().get(1).setHorarioCierre(LocalDateTime.now().plusHours(5));
      servicioDeLa2.getIncidentes().get(0).setHorarioCierre(LocalDateTime.now().plusHours(10));

      persist(luki);
      persist(lucho);
      persist(rockandrolleros);
      persist(servicioDeLa1.getIncidentes().get(0));
      persist(servicioDeLa1.getIncidentes().get(1));
      persist(servicioDeLa2.getIncidentes().get(0));
      persist(servicioDeLa1);
      persist(servicioDeLa2);
      persist(establecimientoDeLa1);
      persist(establecimientoDeLa2);
      persist(entidad1);
      persist(entidad2);
    });
  }

}
