package domain.entities.Services;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class LocationService {

    private static LocationService instancia = null;
    private Retrofit retrofit;

    public LocationService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://api.mercadolibre.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static LocationService instancia(){
        if(instancia== null){
            instancia = new LocationService();
        }
        return instancia;
    }

    public List<PaisAPI> listadoDePaises() throws IOException {
        GeoService georefService = this.retrofit.create(GeoService.class);
        Call<List<PaisAPI>> requestPaises = georefService.paises();
        Response<List<PaisAPI>> responsePaises = requestPaises.execute();
        List<PaisAPI> paises = responsePaises.body();
        return paises;
    }

    public List<ProvinciaAPI> listadoDeProvincias(PaisAPI pais) throws IOException {
        GeoService georefService = this.retrofit.create(GeoService.class);
        Call<List<ProvinciaAPI>> requestProvincias = georefService.provincias(pais.id);
        Response<List<ProvinciaAPI>> responseProvincias = requestProvincias.execute();
        List<ProvinciaAPI> provincias = responseProvincias.body();
        return provincias;
    }

    public List<CiudadAPI> listadoDeCiudades(ProvinciaAPI provincia) throws IOException {
        GeoService georefService = this.retrofit.create(GeoService.class);
        Call<List<CiudadAPI>> requestCiudades = georefService.ciudades(provincia.id);
        Response<List<CiudadAPI>> responseCiudades = requestCiudades.execute();
        List<CiudadAPI> ciudades = responseCiudades.body();
        return ciudades;
    }

    public List<MonedaAPI> listadoDeMonedas() throws IOException {
        GeoService georefService = this.retrofit.create(GeoService.class);
        Call<List<MonedaAPI>> requestMonedas = georefService.monedas();
        Response<List<MonedaAPI>> responseMonedas = requestMonedas.execute();
        List<MonedaAPI> monedaAPIS = responseMonedas.body();
        return monedaAPIS;
    }

}
