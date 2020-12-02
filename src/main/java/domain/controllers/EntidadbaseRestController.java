package domain.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Entidades.Entidadbase;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;


public class EntidadbaseRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response) {
        Entidadbase entidadbase = new Gson().fromJson(request.body(), Entidadbase.class);
        FactoryRepositorio.get(Entidadbase.class).agregar(entidadbase);
        response.type("application/json");
        return new JSONObject().put("id", entidadbase.getIdEntidadBase()).toString();
    }

    public String existe(Request request, Response response){
        response.type("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Entidadbase.class).existe(new Integer(request.params("id"))));
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));
        Entidadbase entidadbase = FactoryRepositorio.get(Entidadbase.class).buscar(new Integer(request.params("id")));
        String result = new JSONObject().toString();
        if (entidadbase != null){
            result = (entidadJuridica.getEntidadesbase().stream().anyMatch(x-> (x.getIdEntidadBase() == entidadbase.getIdEntidadBase())))? (jsonHelper.convertirAJson(entidadbase)):(new JSONObject().toString());
        }
        response.type("application/json");
        return result;
    }

    public String modificar(Request request, Response response){
        Entidadbase entidadbase = new Gson().fromJson(request.body(), Entidadbase.class);
        FactoryRepositorio.get(EntidadJuridica.class).modificar(entidadbase);
        response.type("application/json");
        return new JSONObject().put("id", entidadbase.getIdEntidadBase()).toString();
    }

    public String eliminar(Request request, Response response){
        Entidadbase entidadbase = FactoryRepositorio.get(Entidadbase.class).buscar(new Integer(request.params("id")));
        entidadbase.darBaja();
        FactoryRepositorio.get(EntidadJuridica.class).modificar(entidadbase);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodosDeEntJuridica(Request request, Response response) {
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));
        List<Entidadbase> entidadbase = FactoryRepositorio.get(Entidadbase.class).buscarTodos();
        entidadbase = entidadbase.stream().filter(x -> x.getEntidadJuridica().getIdEntidadJuridica() == entidadJuridica.getIdEntidadJuridica()).collect(Collectors.toList());
        response.type("application/json");
        return jsonHelper.convertirAJson(entidadbase);
    }

    public String mostrarTodos(Request request, Response response){
        List<Entidadbase> entidadbase = FactoryRepositorio.get(Entidadbase.class).buscarTodos();
        entidadbase = entidadbase.stream().filter(x -> x.estaActivo()).collect(Collectors.toList());
        response.type("application/json");
        return jsonHelper.convertirAJson(entidadbase);
    }

}