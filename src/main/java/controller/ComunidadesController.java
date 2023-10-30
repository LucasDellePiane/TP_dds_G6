package controller;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.servicio.EstadoIncidente;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadesController {
  public ModelAndView listar(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    request.queryParams("estadoIncidente");

    // Descomentar cuando el login este hecho
    //Integer id = request.session().attribute("id_usuario");
    //Usuario usuario = RepositorioDeUsuarios.getINSTANCE().findById(id);
    //List<Comunidad> comunidades = RepositorioComunidad.getInstancia().comunidadesALasQuePertenece(usuario);

    List<Comunidad> comunidades = RepositorioComunidad.getInstancia().obtenerTodos(); // para probar
    List<List<Incidente>> listaincidentes = comunidades.stream().map(comunidad -> {

      // Descomentar cuando el login este hecho
      //EstadoIncidente estado = EstadoIncidente.valueOf(request.queryParams("estadoIncidentes"));;
      //return comunidad.consultarIncidentesPorEstado(estado);

      return comunidad.incidentesReportados(); // para probar
    }).toList();
    modelo.put("incidentes", listaincidentes);
    return new ModelAndView(modelo, "comunidades.html.hbs");
  }
}
