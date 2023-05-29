package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.localizacion.Localizacion;
import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoDepartamentos;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoMuncipios;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoProvincias;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacion;

public class AsesorLocalizacion {
  private ServicioLocalizacion servicioLocalizacion;
  private ListadoDepartamentos listadoDepartamentos;
  private ListadoMuncipios listadoMuncipios;
  private ListadoProvincias listadoProvincias;

  public AsesorLocalizacion(ServicioLocalizacion servicioLocalizacion){
    this.servicioLocalizacion = servicioLocalizacion;
    this.listadoProvincias = servicioLocalizacion.listadoDeProvincias();
  }


  private Provincia buscarProvincia(String nombreProvincia){
    if(this.listadoProvincias.buscar(nombreProvincia) != null){
      return this.listadoProvincias.buscar(nombreProvincia);
    }else{
      throw new RuntimeException("no se encontro la provincia");
    }
  }
  private Division buscarDepartamento(String nombreDepartamento, int idProvincia){
    this.listadoDepartamentos = servicioLocalizacion.listadoDeDepartamentosDeProvincia(idProvincia);
    if(this.listadoDepartamentos.buscar(nombreDepartamento) != null){
      return this.listadoDepartamentos.buscar(nombreDepartamento);
    }else{
      throw new RuntimeException("no se encontro el departamento");
    }
  }
  private Division buscarMunicipio(String nombreMunicipio, int idProvincia){
    this.listadoMuncipios = servicioLocalizacion.listadoDeMunicipiosDeProvincia(idProvincia);
    if(this.listadoMuncipios.buscar(nombreMunicipio) != null){
      return this.listadoMuncipios.buscar(nombreMunicipio);
    }else{
      throw new RuntimeException("no se encontro el departamento");
    }
  }
  public Localizacion generarLocalizacion(String provincia, String division){
    try{
      Provincia provinciaLocalizacion = this.buscarProvincia(provincia);
      Division divisionLocalizacion = this.buscarDepartamento(division,provinciaLocalizacion.getId());
      if(divisionLocalizacion == null){
        Division municipio = this.buscarMunicipio(division,provinciaLocalizacion.getId());
        // IMPORTANTE: TENDRIA Q SETEARLE EL ENUM DE SI ES MUNICIPIO O DEPTO EN SERVICIOLOCALIZACIONGEOREFAPI o aca?
        return new Localizacion(provinciaLocalizacion,municipio);
      }
      return new Localizacion(provinciaLocalizacion,divisionLocalizacion);
    }catch (RuntimeException exception){
      throw new RuntimeException("datos invalidos para la localizacion");
    }
  }
}
