package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeUsuarios;

public class main {
    // agregar tiempo de cada una semana para

    // dos tipos de notificaciones:
    // 1) una es en X tiempo para mostrar los incidentes (cronJob)
    // 2) cuando esta cerca de un incidente

    // crontab



    public void notificarIncidentes() {
      RepositorioDeUsuarios repousuario = RepositorioDeUsuarios.getINSTANCE();
      repousuario.getUsuariosDeLaPlataforma()
          .forEach(usuario->usuario.notificarIncidente());
    }

    /*
     * 
     * //!! repousuario.getInstancia().forEach(obtenerUbicacionActual)
     * 
     * 
     * 
     * repoUsuariogetInstancia().getUsuarios().forEach(usuario->usuario.notificacionIncidente(usuario)

     *
     *
     */
}
