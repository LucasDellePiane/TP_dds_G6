package ar.edu.utn.frba.dds.testIncidente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.Comunidad.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.notificacion.Email;
import ar.edu.utn.frba.dds.domain.notificacion.MedioComunicacion;
import ar.edu.utn.frba.dds.domain.notificacion.WhatsApp;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.servicio.TipoServicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import ar.edu.utn.frba.dds.domain.usuario.ValidadorPeorContrasenia;
import ar.edu.utn.frba.dds.exceptions.RutaInvalidaException;
import ar.edu.utn.frba.dds.exceptions.SeEnvioWhatsappException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidacionEnvioIncidentes {
  Usuario usuario = new Usuario("usuario1", "elmascapodelmundo");
  Usuario usuario2 = new Usuario("usuario2", "elmascapodelmundo");
  List<Usuario> miembros = new ArrayList<>(Arrays.asList(usuario, usuario2));
  List<Usuario> administradores = new ArrayList<>(Arrays.asList(usuario));
  Servicio servicio = new Servicio(TipoServicio.BAÑO);
  List<Servicio> servicios = new ArrayList<>(Arrays.asList(servicio));

  MedioComunicacion whatsApp = new WhatsApp();
  MedioComunicacion email = new Email();
  List<MedioComunicacion> mediosComunicacion = new ArrayList<>(Arrays.asList(whatsApp,email));
  Comunidad comunidad = new Comunidad(miembros, administradores, servicios,mediosComunicacion);
  List<Comunidad> comunidades = new ArrayList<>(Arrays.asList(comunidad));
  boolean repositorioComunidad = RepositorioComunidad.getInstancia().getComunidades().add(comunidad);


  @Test
  public void reportarUnIncidenteAvisaALosUsuarios() {
    usuario.setServiciosInteres(servicios);
    usuario2.setServiciosInteres(servicios);

    assertEquals(comunidad.usuarioEsParte(usuario), true);
    assertEquals(usuario.comunidadesDelUsuario(), comunidades);

    Incidente incidente = usuario.informarNoFuncionamiento(servicio, "Inodoro roto");
    List<Incidente> incidentes = new ArrayList<>(Arrays.asList(incidente));

    assertEquals(servicio.getIncidentes(), incidentes);
    assertEquals(incidentes, comunidad.getIncidentesReportados());

    usuario2.suscribirseMedioComunicacion(whatsApp);
    List<Usuario> suscriptoresWhatsApp = new ArrayList<>(Arrays.asList(usuario2));
    assertEquals(whatsApp.getUsuariosSuscriptos(), suscriptoresWhatsApp);

    assertThrows(SeEnvioWhatsappException.class, ()->{
      usuario.informarNoFuncionamiento(servicio, "Inodoro roto");
    });
  }

}
