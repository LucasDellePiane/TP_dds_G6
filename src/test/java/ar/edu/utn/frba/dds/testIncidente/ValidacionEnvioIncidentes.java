package ar.edu.utn.frba.dds.testIncidente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.Comunidad.RepositorioComunidad;
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
  Usuario usuario = new Usuario("hola", "elmascapodelmundo");
  Usuario usuario2 = new Usuario("chau", "elmascapodelmundo");
  List<Usuario> miembros = new ArrayList<>(Arrays.asList(usuario, usuario2));
  List<Usuario> administradores = new ArrayList<>(Arrays.asList(usuario));
  Servicio servicio = new Servicio(TipoServicio.BAÑO);
  List<Servicio> servicios = new ArrayList<>(Arrays.asList(servicio));
  Comunidad comunidad = new Comunidad(miembros, administradores, servicios);
  List<Comunidad> comunidades = new ArrayList<>(Arrays.asList(comunidad));
  boolean repositorioComunidad = RepositorioComunidad.getInstancia().getComunidades().add(comunidad);


  @Test
  public void reportarUnIncidenteAvisaALosUsuarios() {
    usuario2.setServiciosInteres(servicios);
    comunidad.darDeAltaMiembro(usuario);
    usuario.setServiciosInteres(servicios);
    MedioComunicacion medioComunicacion = new WhatsApp();
    medioComunicacion.suscribirUsuario(usuario);
    medioComunicacion.suscribirUsuario(usuario2);
    Incidente incidente = usuario.informarNoFuncionamiento(servicio, "Inodoro roto");
    List<Incidente> incidentes = new ArrayList<>(Arrays.asList(incidente));

    assertEquals(servicio.getIncidentes(), incidentes);
    assertEquals(comunidad.usuarioEsParte(usuario), true);
    assertEquals(usuario.comunidadesDelUsuario(), comunidades);

    assertEquals(incidentes, comunidad.getIncidentesReportados());

    usuario2.suscribirseMedioComunicacion(medioComunicacion);
    assertEquals(servicio.getIncidentes(), incidentes);

    assertThrows(SeEnvioWhatsappException.class, ()->{
      comunidad.notificarIncidente(servicio);
    });}

}
