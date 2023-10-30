package controller;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioEstablecimientos;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class EstablecimientosController implements WithSimplePersistenceUnit {



  public ModelAndView establecimientos(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("establecimientos", RepositorioEstablecimientos.getInstancia().obtenerTodos());
    return new ModelAndView(modelo, "establecimiento.html.hbs"); // cambiar esto del index
  }

  public ModelAndView servicios(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    Establecimiento establecimiento = RepositorioEstablecimientos.getInstancia().buscar(Long.parseLong(request.params("id")));
    modelo.put("establecimiento", establecimiento);
    return new ModelAndView(modelo, "servicios_establecimiento.html.hbs"); // cambiar esto del index
  }

  public ModelAndView abrirIncidente(Request request, Response response){
    Map<String, Object> modelo = new HashMap<>();
    return new ModelAndView(modelo, "abrir_incidente.html.hbs");
  }
}
