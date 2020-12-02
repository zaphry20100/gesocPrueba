package domain.entities.VinculacionOperaciones;

import domain.entities.VinculacionOperaciones.Models.NombreCriterio;
import domain.entities.VinculacionOperaciones.Models.OperacionEntrada;
import domain.entities.VinculacionOperaciones.Models.OperacionSalida;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface Vinculador {

    @POST("Vinculador/VincularOperaciones")
    Call<OperacionSalida> vincularOperaciones(@Body OperacionEntrada operacionEntrada);

    @GET("Vinculador/criteriosVinculacion")
    Call<List<NombreCriterio>> obtenerCriteriosVinculacion();

}
