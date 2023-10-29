package controller;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeEntidades;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioEstablecimientos;
import ar.edu.utn.frba.dds.domain.servicio.EstadoIncidente;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoController implements WithSimplePersistenceUnit {

  public ModelAndView home(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
//    Integer id = request.session().attribute("user_id");
//    Usuario usuario = RepositorioDeUsuarios.getINSTANCE().findById(id);
//    List<Comunidad> comunidades = RepositorioComunidad.getInstancia().comunidadesALasQuePertenece(usuario);
//    List<List<Incidente>> listaincidentes = comunidades.stream().map(comunidad -> {
//      EstadoIncidente estado = EstadoIncidente.valueOf(request.queryParams("estadoIncidentes"));;
//      return comunidad.consultarIncidentesPorEstado(estado);
//    }).toList();
//    modelo.put("incidentes", listaincidentes);
    return new ModelAndView(modelo, "index.html.hbs");
  }

  public ModelAndView establecimientos(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("establecimientos", RepositorioEstablecimientos.getInstancia().obtenerTodos());
    return new ModelAndView(modelo, "index.html.hbs"); // cambiar esto del index
  }
}
