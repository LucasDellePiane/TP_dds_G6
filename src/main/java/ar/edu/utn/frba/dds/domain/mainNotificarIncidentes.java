package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.repositorios.RepositorioDeUsuarios;

public class mainNotificarIncidentes {

    // dos tipos de notificaciones:
    // 1) una es en X tiempo para mostrar los incidentes (cronJob)
    // 2) cuando esta cerca de un incidente

    public static void notificarIncidentes() { // ej: se corre cada 1 hora
      RepositorioDeUsuarios repousuario = RepositorioDeUsuarios.getINSTANCE();
      repousuario.getUsuariosDeLaPlataforma()
          .forEach(usuario->usuario.notificarIncidentes());
    }
}


