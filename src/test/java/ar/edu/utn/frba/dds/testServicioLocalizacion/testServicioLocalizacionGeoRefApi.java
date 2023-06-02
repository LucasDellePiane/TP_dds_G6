package ar.edu.utn.frba.dds.testServicioLocalizacion;

import ar.edu.utn.frba.dds.domain.AsesorLocalizacion;
import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import ar.edu.utn.frba.dds.domain.localizacion.division.TipoDivision;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoDepartamentos;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoProvincias;
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

  public void unaLocalizacionIncorrectaTiraExcepcion() {
    ServicioLocalizacion servicioMock = Mockito.mock(ServicioLocalizacion.class);

    AsesorLocalizacion asesor = new AsesorLocalizacion(servicioMock);
    Provincia p1 = new Provincia("Buenos Aires", 1);
    Provincia p2= new Provincia("Santa Fe", 2);
    List<Provincia> provincias = new ArrayList<>(Arrays.asList());
    ListadoProvincias listadoProvincias = new ListadoProvincias(provincias.size(),1,0,provincias);
    ListadoDepartamentos listadoDepartamentos = new ListadoDepartamentos();
    Division division = new Division("PALPALÁ", 38042, TipoDivision.DEPARTAMENTO);
    List<Division> divisiones = new ArrayList<>(Arrays.asList());
    listadoDepartamentos.setDepartamentos(divisiones);

    Mockito
        .when(servicioMock.listadoDeProvincias())
        .thenReturn(listadoProvincias);
    Mockito
        .when(servicioMock.listadoDeDepartamentosDeProvincia(86))
        .thenReturn(listadoDepartamentos);

    Usuario usuario = new Usuario("Hola", "diseño de sistemas");
    Provincia provincia = new Provincia("Santiado del Estero", 86);
    Localizacion localizacion = new Localizacion(provincia, division);
    localizacion.setAsesorLocalizacion(asesor);
    Assertions.assertThrows(LocalizacionInvalidaException.class, () -> {
      usuario.configurarLocalizacion(localizacion);
    });
  }

  @Test
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

  }
}
