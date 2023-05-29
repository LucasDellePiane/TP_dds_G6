package com.georef;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoDepartamentos;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoMuncipios;
import ar.edu.utn.frba.dds.domain.servicioLocalizacion.ListadoProvincias;

public interface GeoRefApi {
  @GET("provincias")
  Call<ListadoProvincias> provincias();

  @GET("municipios")
  Call<ListadoMuncipios> municipiosDeProvincia(@Query("provincia") int idProvincia, @Query("max") int max);

  @GET("departamentos")
  Call<ListadoDepartamentos> departamentosDeProvincia(@Query("provincia") int idProvincia, @Query("max") int max);

}
