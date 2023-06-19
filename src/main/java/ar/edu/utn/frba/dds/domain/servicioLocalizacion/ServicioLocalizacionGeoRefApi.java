package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.localizacion.division.Division;
import ar.edu.utn.frba.dds.domain.localizacion.division.TipoDivision;
import com.georef.GeoRefApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class ServicioLocalizacionGeoRefApi implements ServicioLocalizacion {
  private static final String apiURL = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;

  public ServicioLocalizacionGeoRefApi() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(apiURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @Override
  public Provincia buscarProvincia(String nombreProvincia) {
    try{
      GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

      Call<Provincia> requestProvincia = georefService.provincia(nombreProvincia);

      Response<Provincia> provinciaResponse = requestProvincia.execute();

      provinciaResponse.code();// <- 200

      return provinciaResponse.body();
    } catch (IOException exception){
      throw new RuntimeException("No se pudo realizar la consulta con GeoRefApi correctamente");
    }
  }

  @Override
  public Division buscarMunicipio(String nombreProvincia, String nombreMunicipio) {
    try{
      GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

      Call<Division> resquestMunicipio = georefService.municipioDeProvincia(nombreProvincia, nombreMunicipio);

      Response<Division> municipioResponse = resquestMunicipio.execute();

      Division municipio = municipioResponse.body();

      municipio.setTipo(TipoDivision.MUNICIPIO);

      return municipio;

    }catch (IOException exception){
      throw new RuntimeException("No se pudo realizar la consulta con GeoRefApi correctamente");
    }

  }

  @Override
  public Division buscarDepartamento(String nombreProvincia, String nombreDepartamento) {
    try {
      GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

      Call<Division> requestDepartamento = georefService.departamentoDeProvincia(nombreProvincia, nombreDepartamento);

      Response<Division> departamentoResponse = requestDepartamento.execute();

      Division departamento = departamentoResponse.body();

      departamento.setTipo(TipoDivision.DEPARTAMENTO);

      return departamento;

    } catch (IOException excepcion) {
      throw new RuntimeException("No se pudo realizar la consulta con GeoRefApi correctamente");
    }
  }

}
