package ar.edu.utn.frba.dds.rankingsTests;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.Ranking.RankingPorCantidad;
import ar.edu.utn.frba.dds.domain.Ranking.RankingPorPromedioCierre;
import ar.edu.utn.frba.dds.domain.entidad.Entidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeEntidades;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.servicio.TipoServicio;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacion;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankingTests {
  private RepositorioDeEntidades repoEntidades;
  private Entidad entidad1;
  private Entidad entidad2;
  private Establecimiento establecimientoDeLa1;
  private Establecimiento establecimientoDeLa2;
  private Servicio servicioDeLa1;
  private Servicio servicioDeLa2;

  private Incidente incidente1dela1;
  private Incidente incidente2dela1;
  private Incidente incidente1dela2;
  private Usuario luki;
  private Usuario lucho;
  private Comunidad rockandrolleros;
  RankingPorCantidad rankingCantidad;
  RankingPorPromedioCierre rankingPromedio;
  @BeforeEach
  public void setUp() {
    repoEntidades = new RepositorioDeEntidades();//RepositorioDeEntidades.getInstancia();
    entidad1 = new Entidad();
    entidad1.setearNombre("SuperHeroes");
    entidad2 = new Entidad();
    entidad2.setearNombre("SuperVillanos");
    establecimientoDeLa1 = new Establecimiento();
    establecimientoDeLa2 = new Establecimiento();
    servicioDeLa1 = new Servicio(TipoServicio.BAÑO);
    servicioDeLa2 = new Servicio(TipoServicio.BAÑO);
    luki = new Usuario("usuario1", "elmascapodelmundo");
    lucho = new Usuario("usuario2", "elmascapodelmundo");
    List<Usuario> miembros = new ArrayList<>(Arrays.asList(luki, lucho));
    List<Usuario> administradores = new ArrayList<>(Arrays.asList(luki));
    List<Servicio> servicios = new ArrayList<>(Arrays.asList(servicioDeLa1, servicioDeLa2));
    rockandrolleros =new Comunidad(miembros, administradores, servicios);
    incidente1dela1 = new Incidente("Se tapo el baño", rockandrolleros);
    incidente2dela1 = new Incidente("No hay agua", rockandrolleros);
    incidente1dela2 = new Incidente("no anda la cadena", rockandrolleros);
    RepositorioComunidad.getInstancia().aniadirComunidad(rockandrolleros);
    repoEntidades.aniadirEntidad(entidad1);
    repoEntidades.aniadirEntidad(entidad2);
    entidad1.aniadirEstablecimiento(establecimientoDeLa1);
    entidad2.aniadirEstablecimiento(establecimientoDeLa2);
    establecimientoDeLa1.darAltaServicio(servicioDeLa1);
    establecimientoDeLa2.darAltaServicio(servicioDeLa2);
    rockandrolleros.reportarIncidente(incidente1dela1);
    rockandrolleros.reportarIncidente(incidente2dela1);
    rockandrolleros.reportarIncidente(incidente2dela1);
    incidente1dela1.cerrarIncidente();
    incidente1dela2.cerrarIncidente();
    incidente2dela1.cerrarIncidente();
    incidente1dela1.setHorarioCierre(LocalDateTime.now().plusHours(5));
    incidente2dela1.setHorarioCierre(LocalDateTime.now().plusHours(5));
    incidente1dela2.setHorarioCierre(LocalDateTime.now().plusHours(10));
    rankingCantidad = new RankingPorCantidad();
    rankingPromedio = new RankingPorPromedioCierre();

    servicioDeLa1.aniadirIncidente(incidente1dela1);
    servicioDeLa1.aniadirIncidente(incidente2dela1);
    servicioDeLa2.aniadirIncidente(incidente1dela2);

    repoEntidades.aniadirCriterio(rankingCantidad);
    repoEntidades.aniadirCriterio(rankingPromedio);
    repoEntidades.generarRankings();
  }

  @Test
  public void seCreanLosCsv() {
    assertTrue(verificarExistenciaArchivo("RankingsCSV/rankingPorCantidad.csv"));
  }

  private boolean verificarExistenciaArchivo(String nombreArchivo) {
    File archivo = new File(nombreArchivo);
    return archivo.exists();
  }
  @Test
  public void funcionaRankingPorCantidad() {
    String rutaArchivo = "RankingsCSV/rankingPorCantidad.csv";
    List<String> contenidoLeido = leerArchivoCSV(rutaArchivo);
    //La entidad SuperHeroes tuvo 2 incidentes, supervillanos 1
    String primeraEntidad = "1, SuperHeroes";
    String segundaEntidad = "2, SuperVillanos";
    assertEquals(primeraEntidad, contenidoLeido.get(0));
    assertEquals(segundaEntidad, contenidoLeido.get(1));
    }

  @Test
  public void funcionaRankingPorRangoHorario() {
    String rutaArchivo = "RankingsCSV/rankingPorPromedio.csv";
    List<String> contenidoLeido = leerArchivoCSV(rutaArchivo);
    //Deberia terminar primero super villanos y 2do super heroes
    String primeraEntidad = "1, SuperVillanos";
    String segundaEntidad = "2, SuperHeroes";
    assertEquals(primeraEntidad, contenidoLeido.get(0));
    assertEquals(segundaEntidad, contenidoLeido.get(1));
  }
  @Test
  public void elPromedioDeCierreSeCalculaBien() {
    assertEquals(10, entidad2.promedioDeCierreIncidente());
    assertEquals(5, entidad1.promedioDeCierreIncidente());
  }
  @Test public void cantidadDeIncidentesSemanales() {
    assertEquals(2, entidad1.cantidadIncidentesEnUnaSemana());
  }
  @Test public void cantidadDeIncidentes() {
    assertEquals(2, entidad1.cantidadIncidentesEntidad());
  }

  @Test public void elTiempoDeCierreEstaBien() {
    assertEquals(5, incidente1dela1.tiempoCierre());
  }

  private List<String> leerArchivoCSV(String rutaArchivo) {
    List<String> lineas = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
      String linea;
      while ((linea = br.readLine()) != null) {
        lineas.add(linea);
      }
    } catch (FileNotFoundException e) {
      throw new RutaInvalidaException("No se encontro el csv");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return lineas;
    }



}
