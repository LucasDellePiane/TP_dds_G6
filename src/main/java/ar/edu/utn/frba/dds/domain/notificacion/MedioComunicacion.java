package ar.edu.utn.frba.dds.domain.notificacion;

import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import lombok.Getter;
import java.util.List;

public interface MedioComunicacion {

    public  void suscribirUsuario(Usuario usuario);
    public  void enviarNotifiacion(List<Usuario> usuarios);


    public  List<Usuario> getUsuariosSuscriptos();
}


