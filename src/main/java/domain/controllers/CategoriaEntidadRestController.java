package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.ContextAPI.RequestCategoriasEntidad;
import domain.entities.Models.ContextAPI.RequestRecategorizar;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaEntidadRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crearCategorias(Request request, Response response){
        response.type("application/json");
        RequestCategoriasEntidad requestCategoriasEntidad = new Gson().fromJson(request.body(), RequestCategoriasEntidad.class);
        requestCategoriasEntidad.categoriasEntidad.forEach(x -> {
            FactoryRepositorio.get(CategoriaEntidad.class).agregar(x);
        });
        return new JSONObject().toString();
    }

    public String recategorizar(Request request, Response response){
        response.type("application/json");
        RequestRecategorizar requestRecategorizar = new Gson().fromJson(request.body(), RequestRecategorizar.class);
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(requestRecategorizar.idEntidadJuridica);
        entidadJuridica.categorizar(entidadJuridica,requestRecategorizar);
        FactoryRepositorio.get(EntidadJuridica.class).modificar(entidadJuridica);

        return  new JSONObject().toString();
    }

    public String mostrar(Request request, Response response) {
        CategoriaEntidad categoriaEntidad = FactoryRepositorio.get(CategoriaEntidad.class).buscar(new Integer(request.params("id")));
        String jsonObject = (categoriaEntidad!=null) ? (jsonHelper.convertirAJson(categoriaEntidad)):(new JSONObject().toString());
        response.type("application/json");
        return  jsonObject;

    }


}
