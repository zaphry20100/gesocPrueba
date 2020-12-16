package domain.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

        // --------------- MODIFICAR ------------------
        FactoryRepositorio.get(Egreso.class).modificar(egreso);
        FactoryRepositorio.get(Presupuesto.class).modificar(presupuestoElegido);

    }

}