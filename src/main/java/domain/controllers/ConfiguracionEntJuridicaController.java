package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class ConfiguracionEntJuridicaController {

    JSONHelper jsonHelper = new JSONHelper();

    public String mostrar(Request request, Response response){
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("id")));
        ConfiguracionEntidadJuridica configuracionEntidadJuridica = entidadJuridica.getConfiguracionEntidadJuridica();
        String jsonObject = (configuracionEntidadJuridica!=null) ? (jsonHelper.convertirAJsonPrettyPlease(configuracionEntidadJuridica)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        ConfiguracionEntidadJuridica configuracionEntidadJuridica = new Gson().fromJson(request.body(), ConfiguracionEntidadJuridica.class);
        FactoryRepositorio.get(ConfiguracionEntidadJuridica.class).modificar(configuracionEntidadJuridica);
        response.type("application/json");
        return new JSONObject().toString();
    }
}
