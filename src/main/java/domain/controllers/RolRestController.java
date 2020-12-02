package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Proveedor;
import domain.entities.Models.Usuarios.Rol;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

public class RolRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Rol rol = new Gson().fromJson(request.body(), Rol.class);
        FactoryRepositorio.get(Proveedor.class).agregar(rol);
        response.type("application/json");
        return new JSONObject().put("id", rol.getIdRol()).toString();
    }

    public String mostrar(Request request, Response response){
        Rol rol = FactoryRepositorio.get(Rol.class).buscar(new Integer(request.params("id")));
        String jsonObject = (rol!=null) ? (jsonHelper.convertirAJson(rol)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String mostrarTodos(Request request, Response response) {
        List<Rol> rols = FactoryRepositorio.get(Rol.class).buscarTodos();
        String result = new JSONObject().toString();
        response.type("application/json");
        if (! rols.isEmpty()){
            result = jsonHelper.convertirAJson(rols);
        }
        return result;
    }

}