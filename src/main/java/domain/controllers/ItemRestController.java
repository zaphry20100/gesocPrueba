package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.*;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

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
        Item itemOriginal = FactoryRepositorio.get(Item.class).buscar(new Integer(request.params("id")));
        itemOriginal.setValor(item.getValor());
        FactoryRepositorio.get(EntidadJuridica.class).modificar(itemOriginal);
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
        int idEntidadJuridica = new Integer(request.params("idEntJur"));
        List<Item> items = FactoryRepositorio.get(Item.class).buscarTodos();
        items = items.stream().filter(x -> x.getEntidadjuridica().getIdEntidadJuridica() == idEntidadJuridica).collect(Collectors.toList());
        String result = new JSONObject().toString();
        response.type("application/json");
        if (! items.isEmpty()){
            result = jsonHelper.convertirAJson(items);
        }
        return result;
    }

}