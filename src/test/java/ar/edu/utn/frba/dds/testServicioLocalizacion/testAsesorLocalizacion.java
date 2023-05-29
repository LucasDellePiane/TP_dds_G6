package ar.edu.utn.frba.dds.testServicioLocalizacion;

import ar.edu.utn.frba.dds.domain.AsesorLocalizacion;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacion;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacionGeoRefApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

// Hay q hacer 3 tipos de test: el de localizacion, el de servicioLocalizacion con el mock y uno mas que es para
// testear georefApi si mal no entiendo
public class testAsesorLocalizacion {
  // Mockito me da la posibilidad de generar un Mock sin la necesidad de crarlos

  @Test
  void sePuedeGenerarLocalizacionParaProvinciaYMuncipio() {
    ServicioLocalizacion servicioMock = Mockito.mock(ServicioLocalizacion.class);
    AsesorLocalizacion asesor = new AsesorLocalizacion(servicioMock);
    Mockito
        .when(servicioMock.listadoDeProvincias())
        .thenReturn([// aca van la listas de provincias]);
    ServicioLocalizacionGeoRefApi asesor = new ServicioLocalizacionGeoRefApi();
  }
}
