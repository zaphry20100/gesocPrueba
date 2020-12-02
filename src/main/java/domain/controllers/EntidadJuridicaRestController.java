package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.ContextAPI.RequestCategoriasEntidad;
import domain.entities.Models.ContextAPI.RequestLogin;
import domain.entities.Models.ContextAPI.RequestRecategorizar;
import domain.entities.Models.ContextAPI.RequestRelacionEntJur;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
import domain.entities.Models.Entidades.Categorizador;
import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import sun.security.krb5.Config;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class EntidadJuridicaRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        EntidadJuridica entidadJuridica = new Gson().fromJson(request.body(), EntidadJuridica.class);
        FactoryRepositorio.get(EntidadJuridica.class).agregar(entidadJuridica);
        this.crearRelaciones(entidadJuridica);
        response.type("application/json");
        return new JSONObject().put("id", entidadJuridica.getIdEntidadJuridica()).toString();
    }

    public String existe(Request request, Response response){
        response.type("application/json");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(EntidadJuridica.class).existe(new Integer(request.params("id"))));
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("id")));
        entidadJuridica.quitarRepetidos();
        String jsonObject = (entidadJuridica!=null) ? (jsonHelper.convertirAJson(entidadJuridica)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        EntidadJuridica entidadJuridica = new Gson().fromJson(request.body(), EntidadJuridica.class);
        this.crearRelaciones(entidadJuridica);
        FactoryRepositorio.get(EntidadJuridica.class).modificar(entidadJuridica);
        response.type("application/json");
        return new JSONObject().put("id", entidadJuridica.getIdEntidadJuridica()).toString();
    }

    public String eliminar(Request request, Response response){
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("id")));
        entidadJuridica.darBaja();
        FactoryRepositorio.get(EntidadJuridica.class).modificar(entidadJuridica);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<EntidadJuridica> entidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class).buscarTodos();
        entidadesJuridicas = entidadesJuridicas.stream().filter(x -> x.estaActivo()).collect(Collectors.toList());
        for (EntidadJuridica entidadJuridica:entidadesJuridicas){
            entidadJuridica.quitarRepetidos();
        }
        response.type("application/json");
        return jsonHelper.convertirListaAJson(entidadesJuridicas);
    }


    public String crearRelacionesExt(Request request, Response response){
        RequestRelacionEntJur requestRelacionEntJur = new Gson().fromJson(request.body(), RequestRelacionEntJur.class);

        requestRelacionEntJur.IdEntidadJuridica.forEach(x -> {

            EntidadJuridica entJur = FactoryRepositorio.get(EntidadJuridica.class).buscar(x);

            // Configuracion EntJuridica
            ConfiguracionEntidadJuridica configuracionEntJuridica = new ConfiguracionEntidadJuridica().setEntidadjuridica(entJur);
            configuracionEntJuridica.getConfigEntidadJuridica().put("CriterioCantidadPresupuestos", "2");
            configuracionEntJuridica.getConfigEntidadJuridica().put("CriterioCompraDeUnPresupuesto", "si");
            configuracionEntJuridica.getConfigEntidadJuridica().put("CriterioMenorValor", "CRITERIO_MENOR_PRECIO");

            entJur.setConfiguracionEntidadJuridica(configuracionEntJuridica);
            FactoryRepositorio.get(ConfiguracionEntidadJuridica.class).agregar(configuracionEntJuridica);

            // Categoria
            entJur.categorizar(entJur);

            // --------------- MODIFICAR ------------------ //
            FactoryRepositorio.get(EntidadJuridica.class).modificar(entJur);


        });


        return new JSONObject().toString();
    }

    private void crearRelaciones(EntidadJuridica entJur){

        // Configuracion EntJuridica
        ConfiguracionEntidadJuridica configuracionEntJuridica = new ConfiguracionEntidadJuridica().setEntidadjuridica(entJur);
        configuracionEntJuridica.getConfigEntidadJuridica().put("CriterioCantidadPresupuestos", "2");
        configuracionEntJuridica.getConfigEntidadJuridica().put("CriterioCompraDeUnPresupuesto", "si");
        configuracionEntJuridica.getConfigEntidadJuridica().put("CriterioMenorValor", "CRITERIO_MENOR_PRECIO");

        entJur.setConfiguracionEntidadJuridica(configuracionEntJuridica);
        FactoryRepositorio.get(ConfiguracionEntidadJuridica.class).agregar(configuracionEntJuridica);

        // Categoria
        entJur.categorizar(entJur);

        // --------------- MODIFICAR ------------------ //
        FactoryRepositorio.get(EntidadJuridica.class).modificar(entJur);
    }

}