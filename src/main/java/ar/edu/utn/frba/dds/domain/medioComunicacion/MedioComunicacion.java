package ar.edu.utn.frba.dds.domain.medioComunicacion;

import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.servicio.Incidente;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import ar.edu.utn.frba.dds.domain.usuario.Usuario;
import java.util.List;

public interface MedioComunicacion {

    public Localizacion notificarIncidente(Usuario usuario, Incidente incidente);

    public void notificarServicioCercano(Usuario usuario, List<Servicio> servicios);
}


