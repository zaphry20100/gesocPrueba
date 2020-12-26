package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Categorias.Criterios.CriterioPresupuesto;
import domain.entities.Models.ContextAPI.RequestRevisores;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Presupuesto;
import domain.entities.Models.Usuarios.Revisor;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriosRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        CriterioPresupuesto criterioPresupuesto = new Gson().fromJson(request.body(), CriterioPresupuesto.class);
        FactoryRepositorio.get(CriterioPresupuesto.class).agregar(criterioPresupuesto);
        this.crearRelaciones(criterioPresupuesto);
        response.type("application/json");
        return new JSONObject().put("id", criterioPresupuesto.getIdCriterioPresupuesto()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(CriterioPresupuesto.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        CriterioPresupuesto criterioPresupuesto  = FactoryRepositorio.get(CriterioPresupuesto.class).buscar(new Integer(request.params("id")));
        //egreso.quitarRepetidos();
        String jsonObject = (criterioPresupuesto!=null) ? (jsonHelper.convertirAJson(criterioPresupuesto)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        CriterioPresupuesto criterioPresupuesto = new Gson().fromJson(request.body(), CriterioPresupuesto.class);
        FactoryRepositorio.get(CriterioPresupuesto.class).modificar(criterioPresupuesto);
        response.type("application/json");
        return new JSONObject().put("id", criterioPresupuesto.getIdCriterioPresupuesto()).toString();
    }

    public String eliminar(Request request, Response response){
        CriterioPresupuesto criterioPresupuesto = FactoryRepositorio.get(CriterioPresupuesto.class).buscar(new Integer(request.params("id")));
        criterioPresupuesto.darBaja();
        FactoryRepositorio.get(CriterioPresupuesto.class).modificar(criterioPresupuesto);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<CriterioPresupuesto> criterioPresupuestos = FactoryRepositorio.get(CriterioPresupuesto.class).buscarTodos();
        for(CriterioPresupuesto criterioPresupuesto:criterioPresupuestos){
            criterioPresupuesto.quitarRepetidos();
        }
        criterioPresupuestos = criterioPresupuestos.stream().filter(x-> x.getEntidadJuridica().getIdEntidadJuridica() == new Integer(request.params("idEntJur"))).collect(Collectors.toList());
        response.type("application/json");
        String result = new JSONObject().toString();
        if (! criterioPresupuestos.isEmpty()){
            result = jsonHelper.convertirAJson(criterioPresupuestos);
        }
        return result;
    }

    private void crearRelaciones(CriterioPresupuesto criterioPresupuesto){
        if(criterioPresupuesto.getCriterioPadre() != 0){
            CriterioPresupuesto criterioPresupuestoPadre = FactoryRepositorio.get(CriterioPresupuesto.class).buscar(criterioPresupuesto.getCriterioPadre());
            criterioPresupuesto.setCriterioPadre(criterioPresupuestoPadre.getIdCriterioPresupuesto());
            FactoryRepositorio.get(CriterioPresupuesto.class).modificar(criterioPresupuesto);
        }
    }
}