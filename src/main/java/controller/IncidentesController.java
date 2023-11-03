package controller;

import ar.edu.utn.frba.dds.domain.Comunidad.Comunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioComunidad;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioIncidentes;
import ar.edu.utn.frba.dds.domain.servicio.EstadoIncidente;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentesController {

  public ModelAndView cerrar(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    request.params("id_inc");

    Incidente incidente = RepositorioIncidentes.getINSTANCE().buscarPorId(Integer.parseInt(request.params("id_inc")));
    incidente.cerrarIncidente();
    response.redirect("/establecimientos");
    return null;
  }


}
