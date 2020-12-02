package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.ContextAPI.RequestCategoriasEntidad;
import domain.entities.Models.ContextAPI.RequestRecategorizar;
import domain.entities.Models.Direccion.Direccion;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
import domain.entities.Models.Entidades.Categorizador;
import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import sun.security.krb5.Config;

import java.util.List;
import java.util.stream.Collectors;


public class DireccionRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Direccion direccion = new Gson().fromJson(request.body(), Direccion.class);
        FactoryRepositorio.get(Direccion.class).agregar(direccion);
        response.type("application/json");
        return new JSONObject().put("id", direccion.getIdDireccion()).toString();
    }

    public String existe(Request request, Response response){
        response.type("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Direccion.class).existe(new Integer(request.params("id"))));
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Direccion direccion = FactoryRepositorio.get(Direccion.class).buscar(new Integer(request.params("id")));
        String jsonObject = (direccion!=null) ? (jsonHelper.convertirAJson(direccion)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Direccion direccion = new Gson().fromJson(request.body(), Direccion.class);
        FactoryRepositorio.get(Direccion.class).modificar(direccion);
        response.type("application/json");
        return new JSONObject().put("id", direccion.getIdDireccion()).toString();
    }

    public String eliminar(Request request, Response response){
        Direccion direccion = FactoryRepositorio.get(Direccion.class).buscar(new Integer(request.params("id")));
        direccion.darBaja();
        FactoryRepositorio.get(Direccion.class).modificar(direccion);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<Direccion> direcciones = FactoryRepositorio.get(Direccion.class).buscarTodos();
        response.type("application/json");
        return jsonHelper.convertirListaAJson(direcciones);
    }


}