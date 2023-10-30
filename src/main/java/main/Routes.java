package main;

import controller.ComunidadesController;
import controller.EstablecimientosController;
import controller.SessionController;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.Spark;
import handlebars.HandlebarsTemplateEngine;
import javax.persistence.PersistenceException;

public class Routes implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    new Bootstrap().run();
    new Routes().start();
  }

  public void start() {
    System.out.println("Iniciando servidor");

    Spark.port(9001);
    Spark.staticFileLocation("/public");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    EstablecimientosController establecimientosController = new EstablecimientosController();
    SessionController sessionController = new SessionController();
    ComunidadesController comunidadesController = new ComunidadesController();

    Spark.get("/establecimientos", establecimientosController::establecimientos, engine);
    Spark.get("/establecimientos/:id", establecimientosController::servicios, engine);
    Spark.get("/servicios/:id/abrirIncidente", establecimientosController::abrirIncidente, engine);
    Spark.get("/login", sessionController::mostrarLogin, engine);
    Spark.get("/loginError", sessionController::mostrarLoginError, engine);
    Spark.post("/login", sessionController::iniciarSesion);
    Spark.get("/comunidades", comunidadesController::listar, engine);

    Spark.exception(PersistenceException.class, (e, request, response) -> {
      response.redirect("/500"); //TODO
    });

    Spark.before((request, response) -> {
      if ( !(request.pathInfo().startsWith("/login") || request.pathInfo().startsWith("/styles"))
          && (request.session().attribute("user_id") == null) ) {
        response.redirect("/login");
      }
      entityManager().clear();
    });
  }


}
