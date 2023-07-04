package ar.edu.utn.frba.dds.domain.Ranking;

import java.util.List;

import ar.edu.utn.frba.dds.domain.entidad.RepositorioDeEntidades;

public class RegistroDeRankings { // cambiar nombre
    private List<Criterio> criterios;

  /*public static void main(){
    //logica para que se haga generar rankings una vez por semana
  } 
      DUDAA !!!
*/

  public void generarRankings(){
    criterios.forEach(criterio -> criterio.calcularRanking(RepositorioDeEntidades.getRepositorioDeEntidades().getEntidades()));
  }
}
