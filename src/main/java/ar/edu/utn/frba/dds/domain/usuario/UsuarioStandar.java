package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.establecimiento.Establecimiento;
import ar.edu.utn.frba.dds.domain.servicio.Servicio;
import java.util.List;

public class UsuarioStandar extends Usuario{
  private List<Establecimiento> establecimientosInteres;
  private List<Servicio> serviciosInteres;
  private String apellido;
  public UsuarioStandar(String nombreUsuario, String contrasenia) {
    super(nombreUsuario, contrasenia);
  }
}
