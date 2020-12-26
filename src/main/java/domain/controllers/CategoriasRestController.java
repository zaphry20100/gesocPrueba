package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Categorias.EgresoXCategoria;
import domain.entities.Models.Categorias.IngresoXCategoria;
import domain.entities.Models.Categorias.PresupuestoXCategoria;
import domain.entities.Models.ContextAPI.RequestCategoriaEgreso;
import domain.entities.Models.ContextAPI.RequestCategoriaIngreso;
import domain.entities.Models.ContextAPI.RequestCategoriaPresupuesto;
import domain.entities.Models.ContextAPI.RequestRevisores;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
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

public class CategoriasRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        CategoriaPresupuesto categoriaPresupuesto = new Gson().fromJson(request.body(), CategoriaPresupuesto.class);
        FactoryRepositorio.get(CategoriaPresupuesto.class).agregar(categoriaPresupuesto);
        this.crearRelaciones(categoriaPresupuesto);
        response.type("application/json");
        return new JSONObject().put("id", categoriaPresupuesto.getIdCategoriaPresupuesto()).toString();
    }

    public String agregarCategoriaEgreso(Request request, Response response){
        RequestCategoriaEgreso requestCategoriaEgreso = new Gson().fromJson(request.body(), RequestCategoriaEgreso.class);
        EgresoXCategoria egresoXCategoria = new EgresoXCategoria();
        egresoXCategoria.setCategoriaPresupuesto(requestCategoriaEgreso.categoria);
        egresoXCategoria.setEgreso(requestCategoriaEgreso.egreso);
        FactoryRepositorio.get(EgresoXCategoria.class).agregar(egresoXCategoria);
        return new JSONObject().put("id", requestCategoriaEgreso.egreso.getIdEgreso()).toString();
    }

    public String agregarCategoriaIngreso(Request request, Response response){
        RequestCategoriaIngreso requestCategoriaIngreso = new Gson().fromJson(request.body(), RequestCategoriaIngreso.class);
        IngresoXCategoria ingresoXCategoria = new IngresoXCategoria();
        ingresoXCategoria.setCategoriaPresupuesto(requestCategoriaIngreso.categoria);
        ingresoXCategoria.setIngreso(requestCategoriaIngreso.ingreso);
        FactoryRepositorio.get(IngresoXCategoria.class).agregar(ingresoXCategoria);
        return new JSONObject().put("id", requestCategoriaIngreso.ingreso.getIdIngreso()).toString();
    }

    public String agregarCategoriaPresupuesto(Request request, Response response){
        RequestCategoriaPresupuesto requestCategoriaPresupuesto = new Gson().fromJson(request.body(), RequestCategoriaPresupuesto.class);
        PresupuestoXCategoria presupuestoXCategoria = new PresupuestoXCategoria();
        presupuestoXCategoria.setCategoriaPresupuesto(requestCategoriaPresupuesto.categoria);
        presupuestoXCategoria.setPresupuesto(requestCategoriaPresupuesto.presupuesto);
        FactoryRepositorio.get(EgresoXCategoria.class).agregar(presupuestoXCategoria);
        return new JSONObject().put("id", requestCategoriaPresupuesto.presupuesto.getIdPresupuesto()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(CategoriaPresupuesto.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        CategoriaPresupuesto categoriaPresupuesto  = FactoryRepositorio.get(CategoriaPresupuesto.class).buscar(new Integer(request.params("id")));
        categoriaPresupuesto.quitarRepetidos();
        String jsonObject = (categoriaPresupuesto!=null) ? (jsonHelper.convertirAJson(categoriaPresupuesto)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        CategoriaPresupuesto categoriaPresupuesto = new Gson().fromJson(request.body(), CategoriaPresupuesto.class);
        FactoryRepositorio.get(Egreso.class).modificar(categoriaPresupuesto);
        response.type("application/json");
        return new JSONObject().put("id", categoriaPresupuesto.getIdCategoriaPresupuesto()).toString();
    }

    public String eliminar(Request request, Response response){
        CategoriaPresupuesto categoriaPresupuesto = FactoryRepositorio.get(CategoriaPresupuesto.class).buscar(new Integer(request.params("id")));
        categoriaPresupuesto.darBaja();
        FactoryRepositorio.get(CategoriaPresupuesto.class).modificar(categoriaPresupuesto);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<CategoriaPresupuesto> categoriaPresupuestos = FactoryRepositorio.get(CategoriaPresupuesto.class).buscarTodos();
        for(CategoriaPresupuesto categoriaPresupuesto:categoriaPresupuestos){
            categoriaPresupuesto.quitarRepetidos();
        }

        categoriaPresupuestos = categoriaPresupuestos.stream().filter(x-> x.getEntidadjuridica().getIdEntidadJuridica() == new Integer(request.params("idEntJur"))).collect(Collectors.toList());
        response.type("application/json");
        String result = new JSONObject().toString();
        if (! categoriaPresupuestos.isEmpty()){
            result = jsonHelper.convertirAJson(categoriaPresupuestos);
        }
        return result;
    }

    private void crearRelaciones(CategoriaPresupuesto egreso){

    }
}