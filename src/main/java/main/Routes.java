package main;

import controller.DemoController;
import controller.SessionController;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Spark;
import handlebars.HandlebarsTemplateEngine;
import javax.persistence.PersistenceException;

public class Routes implements WithSimplePersistenceUnit {

  public static void main(String[] args) {
    /*new Bootstrap().run();*/
    new Routes().start();
  }

  public void start() {
    System.out.println("Iniciando servidor");

    Spark.port(9000);
    Spark.staticFileLocation("/public");

    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    DemoController demoController = new DemoController();
    SessionController sessionController = new SessionController();

    Spark.get("/", demoController::home, engine);
    Spark.get("/login", sessionController::mostrarLogin, engine);

    Spark.exception(PersistenceException.class, (e, request, response) -> {
      response.redirect("/500"); //TODO
    });

    Spark.before((request, response) -> {
      entityManager().clear();
      if(request.session().attribute("user_id")== null)
        response.redirect("/login");
    });
  }


}
