package ar.edu.utn.frba.dds.domain;

import lombok.Getter;

@Getter
public class Usuario {
  private String nombre;
  private String apellido;
  private String email;
  private String contrasenia;
  private String nombreUsuario;

  public Usuario(String nombreUsuario, String contrasenia) {
    this.nombreUsuario = nombreUsuario;
    this.contrasenia = contrasenia;
  }

}

