package controller;

import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeEntidades;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RankingsController implements WithSimplePersistenceUnit {
  public ModelAndView rankings(Request request, Response response) {
    Map<String, Object> modelo = new HashMap<>();
    modelo.put("rankingCantidad", new ArrayList<>(RepositorioDeEntidades.getInstancia().calcularRankingCantidad()));
    modelo.put("rankingCierre", new ArrayList<>(RepositorioDeEntidades.getInstancia().calcularRankingCierre()));
    return new ModelAndView(modelo, "rankings.html.hbs");
  }
}
