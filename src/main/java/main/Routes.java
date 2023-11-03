package main;

import controller.ComunidadesController;
import controller.EstablecimientosController;
import controller.RankingsController;
import controller.SessionController;
import io.github.flbulgarelli.jpa.extras.perthread.PerThreadEntityManagerAccess;
import io.github.flbulgarelli.jpa.extras.perthread.PerThreadEntityManagerProperties;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.Spark;
import handlebars.HandlebarsTemplateEngine;
import javax.persistence.PersistenceException;
import java.util.Optional;

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
    RankingsController rankingsController = new RankingsController();

    Spark.get("/establecimientos", establecimientosController::establecimientos, engine);
    Spark.get("/establecimientos/:id", establecimientosController::servicios, engine);
    Spark.get("/servicios/:id/abrirIncidente", establecimientosController::abrirIncidente, engine);
    Spark.get("/login", sessionController::mostrarLogin, engine);
    Spark.post("/login", sessionController::iniciarSesion);
    Spark.get("/registro", sessionController::mostrarRegistro, engine);
    Spark.post("/registro", sessionController::registrar);
    Spark.get("/comunidades", comunidadesController::listar, engine); // son incidentes x comunidad
    Spark.get("/rankings", rankingsController::rankings, engine);
    Spark.get("/incidentes/:id/cerrar", rankingsController::rankings, engine);
    Spark.get("/restablecerContrasenia", sessionController::mostrarRestablecimiento, engine);
    Spark.post("/restablecerContrasenia", sessionController::restablecimiento);
    Spark.get("/sugerencias", establecimientosController::serviciosCercanos, engine);

    Spark.exception(PersistenceException.class, (e, request, response) -> {
      response.redirect("/500"); //TODO
    });

    Spark.before((request, response) -> {
      if ( !(request.pathInfo().startsWith("/login") || request.pathInfo().startsWith("/styles")
      ||request.pathInfo().startsWith("/registro") ||request.pathInfo().startsWith("/restablecerContrasenia") )
          && (request.session().attribute("user_id") == null) ) {
        response.redirect("/login");
      }
      entityManager().clear();

    });

    Spark.after((request, response) -> {
      System.out.println(request.url());
      System.out.println(Optional.ofNullable(request.session().attribute("user_id")));
      entityManager().clear();
    });

  }


}
