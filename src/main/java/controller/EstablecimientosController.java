package controller;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.repositorios.RepositorioEstablecimientos;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstablecimientosController implements WithSimplePersistenceUnit {

  public ModelAndView establecimientos(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    List<Establecimiento> establecimientos = RepositorioEstablecimientos.getInstancia().obtenerTodos();
    modelo.put("establecimientos", establecimientos);
    modelo.put("path","establecimientos");
    return new ModelAndView(modelo, "establecimiento.html.hbs"); // cambiar esto del index
  }

  public ModelAndView serviciosCercanos(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    List<Establecimiento> establecimientos = RepositorioEstablecimientos.getInstancia().obtenerTodos();
    List<Servicio> serviciosSugeridos = establecimientos.stream().map(establecimiento -> {
      Integer id = request.session().attribute("user_id");
      Usuario usuario = RepositorioDeUsuarios.getINSTANCE().buscarPorId(id);
      List<Servicio> servicios = establecimiento.estaCerca(usuario);
      return servicios;
    }).flatMap(Collection::stream).toList();
    System.out.println(serviciosSugeridos);
    modelo.put("servicios", serviciosSugeridos);
    modelo.put("path","establecimientos");
    return new ModelAndView(modelo, "establecimiento.html.hbs"); // cambiar esto del index
  }

  public ModelAndView servicios(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
//    Establecimiento establecimiento = RepositorioEstablecimientos.getInstancia().buscar(request.params("id"));
//    modelo.put("establecimiento", establecimiento);
    System.out.println(request.params("id"));
    modelo.put("path","establecimientos");
    return new ModelAndView(modelo, "servicios_establecimiento.html.hbs"); // cambiar esto del index
  }

  public ModelAndView abrirIncidente(Request request, Response response){
    Map<String, Object> modelo = new HashMap<>();
    return new ModelAndView(modelo, "apertura_incidente.hbs");
  }
}
