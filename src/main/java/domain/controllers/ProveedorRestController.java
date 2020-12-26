package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Ingreso;
import domain.entities.Models.Transacciones.Proveedor;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class ProveedorRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Proveedor proveedor = new Gson().fromJson(request.body(), Proveedor.class);
        FactoryRepositorio.get(Proveedor.class).agregar(proveedor);
        response.type("application/json");
        return new JSONObject().put("id", proveedor.getIdProveedor()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Proveedor.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Proveedor proveedor = FactoryRepositorio.get(Proveedor.class).buscar(new Integer(request.params("id")));
        String jsonObject = (proveedor!=null) ? (jsonHelper.convertirAJson(proveedor)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Proveedor proveedor = new Gson().fromJson(request.body(), Proveedor.class);
        FactoryRepositorio.get(EntidadJuridica.class).modificar(proveedor);
        response.type("application/json");
        return new JSONObject().put("id", proveedor.getIdProveedor()).toString();
    }

    public String eliminar(Request request, Response response){
        Proveedor proveedor = FactoryRepositorio.get(Proveedor.class).buscar(new Integer(request.params("id")));
        proveedor.darBaja();
        FactoryRepositorio.get(EntidadJuridica.class).modificar(proveedor);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {

        List<Proveedor> proveedores = FactoryRepositorio.get(Proveedor.class).buscarTodos();
        proveedores = proveedores.stream().filter(x-> x.getEntidadJuridica().getIdEntidadJuridica() == new Integer(request.params("idEntJur"))).collect(Collectors.toList());
        String result = new JSONObject().toString();
        response.type("application/json");
        if (! proveedores.isEmpty()){
            result = jsonHelper.convertirAJson(proveedores);
        }
        return result;
    }

}