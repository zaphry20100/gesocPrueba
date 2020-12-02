package domain.entities.Services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface GeoService {

    @GET("classified_locations/countries")
    Call<List<PaisAPI>> paises();

    @GET("classified_locations/countries")
    Call<List<ProvinciaAPI>> provincias(@Query("id") String id);

    @GET("classified_locations/states")
    Call<List<CiudadAPI>> ciudades(@Query("id") String id);

    @GET("currencies")
    Call<List<MonedaAPI>> monedas();
}
