package ar.edu.utn.frba.dds.domain.usuario;

import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import lombok.Getter;

@Getter
public class Usuario {
  private String nombre;
  private String email;
  private String contrasenia;
  private String nombreUsuario;
  private Localizacion localizacion;

  public Usuario(String nombreUsuario, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
  }

  public void configurarLocalizacion(Localizacion nuevaLocalizacion){
    if(nuevaLocalizacion.localizacionValida()){
      localizacion = nuevaLocalizacion;
    }

  }

}


