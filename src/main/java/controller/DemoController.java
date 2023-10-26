package controller;

import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeEntidades;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioEstablecimientos;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class DemoController implements WithSimplePersistenceUnit {

  public ModelAndView home(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("establecimientos", RepositorioEstablecimientos.getInstancia().obtenerTodos());
    return new ModelAndView(modelo, "index.html.hbs");
  }
}
