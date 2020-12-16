package domain.entities.Services;

import domain.entities.VinculacionOperaciones.Models.NombreCriterio;
import domain.entities.VinculacionOperaciones.Models.OperacionEntrada;
import domain.entities.VinculacionOperaciones.Models.OperacionSalida;
import domain.entities.VinculacionOperaciones.Vinculador;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class VinculadorService {

    private static VinculadorService instancia = null;
    private Retrofit retrofit;

    public VinculadorService() {
        // Create a new object from HttpLoggingInterceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add Interceptor to HttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build();


        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://vinculador-gesoc.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static VinculadorService instancia(){
        if(instancia== null){
            instancia = new VinculadorService();
        }
        return instancia;
    }

    public OperacionSalida ejecutarVinculacion(OperacionEntrada operacionEntrada) throws IOException {
        Vinculador vinculadorService = this.retrofit.create(Vinculador.class);
        Call<OperacionSalida> requestOperacionSalida = vinculadorService.vincularOperaciones(operacionEntrada);
        Response<OperacionSalida> responseOperacionSalida = requestOperacionSalida.execute();
        OperacionSalida operacionSalida = responseOperacionSalida.body();

        return operacionSalida;
    }

    public List<NombreCriterio> mostrarCriterios() throws IOException{
        Vinculador vinculadorService = this.retrofit.create(Vinculador.class);
        Call<List<NombreCriterio>> requestCriteriosVinculador = vinculadorService.obtenerCriteriosVinculacion();
        Response<List<NombreCriterio>> responseCriteriosVinculador = requestCriteriosVinculador.execute();
        List<NombreCriterio> criteriosVinculador = responseCriteriosVinculador.body();

        return criteriosVinculador;
    }


}
