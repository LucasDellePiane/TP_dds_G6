package ar.edu.utn.frba.dds.domain.servicioLocalizacion;

import ar.edu.utn.frba.dds.domain.localizacion.Provincia;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ServicioLocalizacion;
import com.georef.GeoRefApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoDepartamentos;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoMuncipios;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoProvincias;

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
  public ListadoProvincias listadoDeProvincias() {
    try{
      GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

      Call<ListadoProvincias> requestListadoDeProvincias = georefService.provincias();

      Response<ListadoProvincias> listadoDeProvinciasResponse = requestListadoDeProvincias.execute();
      listadoDeProvinciasResponse.code();// <- 200

      return listadoDeProvinciasResponse.body();
    } catch (IOException exception){
      throw new RuntimeException("No se pudo realizar la consulta con GeoRefApi correctamente");
    }
  }

  @Override
  public ListadoMuncipios listadoDeMunicipiosDeProvincia(int idProvincia) {
    try{
      GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

      Call<ListadoMuncipios> requestListadoDeMunicipios = georefService.municipiosDeProvincia(idProvincia, 100);

      Response<ListadoMuncipios> listadoDeMunicipiosResponse = requestListadoDeMunicipios.execute();

      return listadoDeMunicipiosResponse.body();
    }catch (IOException exception){
      throw new RuntimeException("No se pudo realizar la consulta con GeoRefApi correctamente");
    }

  }

  @Override
  public ListadoDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia) {
    try {
      GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

      Call<ListadoDepartamentos> requestListadoDeDepartamentos = georefService.departamentosDeProvincia(idProvincia, 100);

      Response<ListadoDepartamentos> listadoDeDepartamentosResponse = requestListadoDeDepartamentos.execute();

      return listadoDeDepartamentosResponse.body();
    } catch (IOException excepcion) {
      throw new RuntimeException("No se pudo realizar la consulta con GeoRefApi correctamente");
    }
  }

}
