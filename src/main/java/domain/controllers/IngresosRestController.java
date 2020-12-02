package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Ingreso;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IngresosRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Ingreso ingreso = new Gson().fromJson(request.body(), Ingreso.class);
        FactoryRepositorio.get(Ingreso.class).agregar(ingreso);
        response.type("application/json");
        return new JSONObject().put("id", ingreso.getIdIngreso()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Ingreso.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Ingreso ingreso = FactoryRepositorio.get(Ingreso.class).buscar(new Integer(request.params("id")));
        ingreso.quitarRepetidos();
        String jsonObject = (ingreso!=null) ? (jsonHelper.convertirAJson(ingreso)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Ingreso ingreso = new Gson().fromJson(request.body(), Ingreso.class);
        FactoryRepositorio.get(Ingreso.class).modificar(ingreso);
        response.type("application/json");
        return new JSONObject().put("id", ingreso.getIdIngreso()).toString();
    }

    public String eliminar(Request request, Response response){
        Ingreso ingreso = FactoryRepositorio.get(Ingreso.class).buscar(new Integer(request.params("id")));
        ingreso.darBaja();
        FactoryRepositorio.get(EntidadJuridica.class).modificar(ingreso);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<Ingreso> ingresos = FactoryRepositorio.get(Ingreso.class).buscarTodos();
        ingresos.forEach(x -> x.quitarRepetidos());
        //List<Ingreso> ingresosFiltrados = new ArrayList<>();
        //ingresosFiltrados = ingresos.stream().filter(x-> x.getEntidadjuridica().getIdEntidadJuridica() == Integer.parseInt(request.splat()[0])).collect(Collectors.toList());
        /*JSONArray array = new JSONArray();
        ingresosFiltrados.forEach(x->{
            array.put(x.toJSON());
        });
        String result = array.toString();
        response.type("application/json");
        System.out.println(result);
        return result;*/
        String result = new JSONObject().toString();
        if (! ingresos.isEmpty()){
            result = jsonHelper.convertirAJson(ingresos);
        }
        response.type("application/json");
        return jsonHelper.convertirAJson(ingresos);
    }

}