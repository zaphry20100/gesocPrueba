package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.*;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

public class ItemRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Item item  = new Gson().fromJson(request.body(), Item.class);
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));
        item.setEntidadjuridica(entidadJuridica);
        FactoryRepositorio.get(Proveedor.class).agregar(item);
        response.type("application/json");
        return new JSONObject().put("id", item.getIdItem()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Item.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Item item = FactoryRepositorio.get(Item.class).buscar(new Integer(request.params("id")));
        String jsonObject = (item!=null) ? (jsonHelper.convertirAJson(item)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Item item = new Gson().fromJson(request.body(), Item.class);
        FactoryRepositorio.get(EntidadJuridica.class).modificar(item);
        response.type("application/json");
        return new JSONObject().put("id", item.getIdItem()).toString();
    }

    public String eliminar(Request request, Response response){
        Item item = FactoryRepositorio.get(Item.class).buscar(new Integer(request.params("id")));
        item.darBaja();
        FactoryRepositorio.get(Item.class).modificar(item);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<Item> presupuestos = FactoryRepositorio.get(Item.class).buscarTodos();
        String result = new JSONObject().toString();
        response.type("application/json");
        if (! presupuestos.isEmpty()){
            result = jsonHelper.convertirAJson(presupuestos);
        }
        return result;
    }

}