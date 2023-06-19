package ar.edu.utn.frba.dds.testServicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import ar.edu.utn.frba.dds.domain.localizacion.division.TipoDivision;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacion;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import ar.edu.utn.frba.dds.exceptions.LocalizacionInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testServicioLocalizacionGeoRefApi {

  @Test

  public void seCreaUnaLocalizacionValida() {
    ServicioLocalizacion servicioMock = Mockito.mock(ServicioLocalizacion.class);

    Provincia p1 = new Provincia("Buenos Aires");
    Division departamento = new Division("Lacar", TipoDivision.DEPARTAMENTO);
    Mockito
        .when(servicioMock.buscarProvincia("buenos aires"))
        .thenReturn(p1);
    Mockito
        .when(servicioMock.buscarDepartamento("Neuquen", "Lacar"))
        .thenReturn(departamento);

    Usuario usuario = new Usuario("Hola", "diseño de sistemas");
    Localizacion localizacion = new Localizacion(
        servicioMock.buscarProvincia("buenos aires"),
        servicioMock.buscarDepartamento("Neuquen", "Lacar"));
    Assertions.assertNotNull(localizacion);
  }

  /* @Test
  void sePuedeGenerarLocalizacionConProvinciaYMuncipio() {
    Usuario user = new Usuario("Luciano","contraloca");
    Provincia p1 = new Provincia("Buenos Aires", 1);
    Provincia p2= new Provincia("Santa Fe", 2);
    List<Provincia> provincias = new ArrayList<>(Arrays.asList(p1, p2));
    ListadoProvincias listadoProvincias = new ListadoProvincias(provincias.size(),1,0,provincias);

    ServicioLocalizacion servicioMock = Mockito.mock(ServicioLocalizacion.class);

    AsesorLocalizacion asesor = new AsesorLocalizacion(servicioMock);
   asesor.setListadoProvincias(listadoProvincias);
    Mockito
        .when(servicioMock.listadoDeProvincias())
        .thenReturn(listadoProvincias);

    asesor.buscarProvincia("Buenos Aires");
    Assertions.assertEquals(asesor.buscarProvincia("Buenos Aires"),p1);

  } */
}
