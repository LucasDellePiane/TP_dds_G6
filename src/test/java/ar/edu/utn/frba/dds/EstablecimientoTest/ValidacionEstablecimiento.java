package ar.edu.utn.frba.dds.EstablecimientoTest;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import ar.edu.utn.frba.dds.domain.localizacion.division.TipoDivision;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.servicio.TipoServicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidacionEstablecimiento {
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
  private Localizacion localizacion;
  private Division division;

  @BeforeEach
  public void setUp() {
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
    establecimientoDeLa1.darAltaServicio(servicioDeLa1);
    establecimientoDeLa2.darAltaServicio(servicioDeLa2);
    division = new Division("Monte Grande", TipoDivision.MUNICIPIO);
    localizacion = new Localizacion("Buenos Aires", division);
  }
  /*
  @Test
  public void estaCercaUsuarioDeEstablecimiento(){
    luki.setLocalizacion_actual(localizacion);
    establecimientoDeLa1.setLocalizacion(localizacion);
    List<Incidente> incidentes = new ArrayList<>(Arrays.asList(incidente1dela1, incidente2dela1));
    servicioDeLa1.setIncidentes(incidentes);
    establecimientoDeLa1.estaCerca(luki);

  }*/

}