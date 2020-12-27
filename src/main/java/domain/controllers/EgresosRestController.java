package domain.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Categorias.EgresoXCategoria;
import domain.entities.Models.ContextAPI.RequestCategoriaEgreso;
import domain.entities.Models.ContextAPI.RequestPresupuestos;
import domain.entities.Models.ContextAPI.RequestRecategorizar;
import domain.entities.Models.ContextAPI.RequestRevisores;
import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.*;
import domain.entities.Models.Usuarios.Revisor;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EgresosRestController{

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Egreso egreso = new Gson().fromJson(request.body(), Egreso.class);
        FactoryRepositorio.get(Egreso.class).agregar(egreso);
        this.crearRelaciones(egreso);
        response.type("application/json");
        return new JSONObject().put("id", egreso.getIdEgreso()).toString();
    }

    public String agregarRevisores(Request request, Response response){

        Egreso egreso  = FactoryRepositorio.get(Egreso.class).buscar(new Integer(request.params("id")));
        RequestRevisores revisores = new Gson().fromJson(request.body(), RequestRevisores.class);

        revisores.revisores.forEach(x->{
            Revisor revisor = new Revisor();
            revisor.setEgreso(egreso);
            revisor.setUsuario(x);
            FactoryRepositorio.get(Revisor.class).agregar(revisor);
        });

        response.type("application/json");
        return new JSONObject().put("id", egreso.getIdEgreso()).toString();
    }


    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Egreso.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Egreso egreso  = FactoryRepositorio.get(Egreso.class).buscar(new Integer(request.params("id")));
        egreso.quitarRepetidos();

        egreso.getCategoriaPresupuestos().forEach( z -> {
            egreso.getIdsCategorias().add(z.getCategoriaPresupuesto().getIdCategoriaPresupuesto());
        });
        egreso.getRevisores().forEach( z -> {
            egreso.getIdsUsuariosRevisores().add(z.getUsuario().getIdUsuario());
        });

        egreso.getListaPresupuestos().forEach(x -> {
            x.quitarRepetidos();
        });


        String jsonObject = (egreso!=null) ? (jsonHelper.convertirAJson(egreso)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Egreso egreso = new Gson().fromJson(request.body(), Egreso.class);
        egreso.setIdEgreso(new Integer(request.params("idEgreso")));
        FactoryRepositorio.get(Egreso.class).modificar(egreso);
        response.type("application/json");
        return new JSONObject().put("id", egreso.getIdEgreso()).toString();
    }

    public String eliminar(Request request, Response response){
        Egreso egreso = FactoryRepositorio.get(Egreso.class).buscar(new Integer(request.params("id")));
        egreso.darBaja();
        FactoryRepositorio.get(EntidadJuridica.class).modificar(egreso);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        int idEntidadJuridica = new Integer(request.params("idEntJur"));
        List<Egreso> egresos = FactoryRepositorio.get(Egreso.class).buscarTodos();
        egresos = egresos.stream().filter(x -> x.getEntidadJuridica().getIdEntidadJuridica() == idEntidadJuridica).collect(Collectors.toList());
        for(Egreso egreso:egresos){
            egreso.quitarRepetidos();
        }
        //List<Egreso> egresosFiltrados = new ArrayList<>();
        //egresosFiltrados = egresos.stream().filter(x-> x.getEntidadJuridica().getIdEntidadJuridica() == Integer.parseInt(request.splat()[0])).collect(Collectors.toList());
        egresos.stream().forEach(x-> {
            x.getListaPresupuestos().stream().forEach(y->{

                x.getPresupuestos().add(y.getIdPresupuesto());
            });
            x.setPresupuestos(x.getPresupuestos().stream().distinct().collect(Collectors.toList()));

            x.getCategoriaPresupuestos().forEach( z -> {
                x.getIdsCategorias().add(z.getCategoriaPresupuesto().getIdCategoriaPresupuesto());
            });
            x.getRevisores().forEach( z -> {
                x.getIdsUsuariosRevisores().add(z.getUsuario().getIdUsuario());
            });

            x.quitarRepetidos();

        });


        response.type("application/json");
        String result = new JSONObject().toString();
        if (! egresos.isEmpty()){
            result = jsonHelper.convertirListaAJson(egresos);
        }
        return result;
    }

    public String APImostrarTodos(Request request, Response response){
        List<Egreso> egresos = FactoryRepositorio.get(Egreso.class).buscarTodos();
        egresos = egresos.stream().filter(x -> x.estaActivo()).collect(Collectors.toList());
        response.type("application/json");
        return jsonHelper.convertirAJson(egresos);
    }

    private void agregarCategoriaEgreso(Egreso egreso){
        egreso.getIdsCategorias().forEach(x -> {
            EgresoXCategoria egresoXCategoria = new EgresoXCategoria();
            egresoXCategoria.setCategoriaPresupuesto(FactoryRepositorio.get(CategoriaPresupuesto.class).buscar(x));
            egresoXCategoria.setEgreso(egreso);
            FactoryRepositorio.get(EgresoXCategoria.class).agregar(egresoXCategoria);
        });
    }


    private void crearRelaciones(Egreso egreso) {

        //Asignar lista de presupuestoss
        egreso.getPresupuestos().stream().forEach(x->{
            Presupuesto presupuestoX = FactoryRepositorio.get(Presupuesto.class).buscar(x);
            presupuestoX.setEgreso(egreso);
            FactoryRepositorio.get(Presupuesto.class).modificar(presupuestoX);
        });

        //Asignar presupuesto elegido
        Presupuesto presupuestoElegido = FactoryRepositorio.get(Presupuesto.class).buscar(egreso.getPresupuestoSeleccionado());
        egreso.setPresupuestoElegido(presupuestoElegido);
        presupuestoElegido.setEgresoAsignado(egreso);

        //Copiar items del presupuesto
        Presupuesto presupuesto = FactoryRepositorio.get(Presupuesto.class).buscar(egreso.getPresupuestoSeleccionado());
        presupuesto.getListaPresupuestoXItem().stream().forEach(y -> {
            Egresoxitem egresoxitem = new Egresoxitem();
            egresoxitem.setValorUnitario(y.getValorUnitario());
            egresoxitem.setCantidad(y.getCantidad());
            egresoxitem.setItem(y.getItem());
            egresoxitem.setEgreso(egreso);
            FactoryRepositorio.get(Egresoxitem.class).agregar(egresoxitem);
        });

        //Crear tabla intermedia de Categoria x Egreso
        agregarCategoriaEgreso(egreso);

        // --------------- MODIFICAR ------------------
        FactoryRepositorio.get(Egreso.class).modificar(egreso);
        FactoryRepositorio.get(Presupuesto.class).modificar(presupuestoElegido);

    }

}