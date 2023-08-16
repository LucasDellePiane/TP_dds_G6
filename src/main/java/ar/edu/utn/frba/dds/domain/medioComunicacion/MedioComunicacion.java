package ar.edu.utn.frba.dds.domain.medioComunicacion;

import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import java.util.List;

public interface MedioComunicacion {

    //public  void enviarNotificacion(String medio, String mensaje);
    public Localizacion notificarIncidentes(Usuario usuario, List<Incidente> incidentes);
    public Localizacion notificarIncidente(Usuario usuario, Incidente incidente);
}


