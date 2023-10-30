package controller;

import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeUsuarios;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SessionController {

  public ModelAndView mostrarLogin(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    return new ModelAndView(modelo, "formulario-login.html.hbs");
  }

  public ModelAndView mostrarLoginError(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("error", true);
    return new ModelAndView(modelo, "formulario-login.html.hbs");
  }

  public Void iniciarSesion(Request request, Response response) {
    try {
      Usuario usuario = RepositorioDeUsuarios.getINSTANCE().buscarPorUsuarioYContrasenia(
          request.queryParams("nombreUsuario"),
          request.queryParams("contrasenia"));

      request.session().attribute("user_id", usuario.getId_usuario());
      response.redirect("/establecimientos"); // TODO aca va a convenir leer el origen
      return null;
    } catch (Exception e) {
        response.redirect("/loginError");
      return null;
    }



  }

}
