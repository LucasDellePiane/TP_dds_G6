package ar.edu.utn.frba.dds.domain.servicio;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.Comunidad.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.funcionalidadRegistroUsuarios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.servicio.TipoServicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Servicio {
  private TipoServicio tipo;
  @Getter
  private List<Incidente> incidentes;

  public Servicio(TipoServicio tipo){
    this.tipo = tipo;
    this.incidentes = new ArrayList<>();
  }

  public void aniadirIncidente(Incidente incidente) {
    incidentes.add(incidente);
    //lo siento mediopasamanos, pero es porque cuando se añade el incidente/crea lo reporta a la comunidad que
    //va a añadirlo y notificarlo a los usuarios como nos pide.
    this.ComunidadesInteresadasEnElServicio().forEach(comunidad-> comunidad.reportarIncidente(this,incidente));
  }

  //no deberia estar aca esta funcion muy probablemente y en realidad la cosa nose si deberia ser con comunidad
  private List<Comunidad> ComunidadesInteresadasEnElServicio(){
    return RepositorioComunidad.getInstancia().getComunidades().stream().
        filter(comunidad -> comunidad.getServiciosDeInteres().contains(this)).toList();
  }

  public List<Incidente> getIncidentes() {
    return this.incidentes;
  }
}
