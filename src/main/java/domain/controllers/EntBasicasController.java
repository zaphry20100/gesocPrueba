package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.ContextAPI.RequestMedioDePago;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Mediodepago;
import domain.entities.Models.Transacciones.Moneda;
import domain.entities.Services.LocationService;
import domain.entities.Services.MonedaAPI;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.util.List;

public class EntBasicasController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crearMedioPago(Request request, Response response){
        RequestMedioDePago mediosdepago = new Gson().fromJson(request.body(), RequestMedioDePago.class );

        for(Mediodepago mediodepago : mediosdepago.listaMediosdepago){
            FactoryRepositorio.get(Mediodepago.class).agregar(mediodepago);
        }

        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarMedioPago(Request request, Response response){
        List<Mediodepago> mediosPago = FactoryRepositorio.get(Mediodepago.class).buscarTodos();
        return jsonHelper.convertirListaAJson(mediosPago);
    }

    public String crearMonedas(Request request, Response response){
        LocationService locationService = LocationService.instancia();
        List<MonedaAPI> monedaAPIS;
        try{
            monedaAPIS = locationService.listadoDeMonedas();

            for(MonedaAPI monedaAPI : monedaAPIS){

                Moneda moneda = new Moneda();
                moneda.id = monedaAPI.id;
                moneda.descripcion = monedaAPI.description;
                moneda.simbolo = monedaAPI.symbol;

                FactoryRepositorio.get(Moneda.class).agregar(moneda);
            }
        }catch(IOException ex){
            System.out.println("No se pueden obtener las listas");
        }
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarMoneda(Request request, Response response){
        List<Moneda> monedas = FactoryRepositorio.get(Moneda.class).buscarTodos();
        return jsonHelper.convertirListaAJson(monedas);
    }



}
