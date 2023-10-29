package controller;

import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SessionController {

  public ModelAndView mostrarLogin(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    return new ModelAndView(modelo, "formulario-login.html.hbs");
  }

//  public Void iniciarSesion(Request request, Response response) {
//    try {
//      Usuario usuario = RepositorioDeUsuarios.getINSTANCE().buscarPorUsuarioYContrasenia(
//          request.queryParams("nombre"),
//          request.queryParams("contrasenia"));
//
//      request.session().attribute("user_id", usuario.getId());
//      usuario.setFechaInicioSesion(LocalDateTime.now());
//      response.redirect("/"); // TODO aca va a convenir leer el origen
//      return null;
//    } catch (Exception e) {
//      response.redirect("/login"); // TODO redirigir agregando un mensaje de error
//      return null;
//    }
//  }

}
