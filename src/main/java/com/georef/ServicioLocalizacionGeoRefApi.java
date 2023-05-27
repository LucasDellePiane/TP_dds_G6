package com.georef;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import ar.edu.utn.frba.dds.domain.localizacion.ListadoDepartamentos;
import ar.edu.utn.frba.dds.domain.localizacion.ListadoMuncipios;
import ar.edu.utn.frba.dds.domain.localizacion.ListadoProvincias;
import com.georef.GeoRefApi;

public class ServicioLocalizacionGeoRefApi {
  private static final String apiURL = "https://apis.datos.gob.ar/georef/api/";
  private Retrofit retrofit;

  public ServicioLocalizacionGeoRefApi() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(apiURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public ListadoProvincias listadoDeProvincias() throws IOException {
    GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

    Call<ListadoProvincias> requestListadoDeProvincias = georefService.provincias();

    Response<ListadoProvincias> listadoDeProvinciasResponse = requestListadoDeProvincias.execute();
    listadoDeProvinciasResponse.code();// <- 200

    return listadoDeProvinciasResponse.body();
  }

  public ListadoMuncipios listadoDeMunicipiosDeProvincia(int idProvincia) throws IOException {
    GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

    Call<ListadoMuncipios> requestListadoDeMunicipios = georefService.municipiosDeProvincia(idProvincia, 100);

    Response<ListadoMuncipios> listadoDeMunicipiosResponse = requestListadoDeMunicipios.execute();

    return listadoDeMunicipiosResponse.body();
  }
  public ListadoDepartamentos listadoDeDepartamentosDeProvincia(int idProvincia) throws IOException {
    GeoRefApi georefService = this.retrofit.create(GeoRefApi.class); // <- OBJETO ANONIMO

    Call<ListadoDepartamentos> requestListadoDeDepartamentos = georefService.departamentosDeProvincia(idProvincia, 100);

    Response<ListadoDepartamentos> listadoDeDepartamentosResponse = requestListadoDeDepartamentos.execute();

    return listadoDeDepartamentosResponse.body();
  }

}
