package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.ContextAPI.RequestItemXPresupuesto;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.*;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PresupuestoRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Presupuesto presupuesto = new Gson().fromJson(request.body(), Presupuesto.class);
        FactoryRepositorio.get(Proveedor.class).agregar(presupuesto);
        this.crearRelaciones(presupuesto);
        response.type("application/json");
        return new JSONObject().put("id", presupuesto.getIdPresupuesto()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Presupuesto.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Presupuesto presupuesto = FactoryRepositorio.get(Presupuesto.class).buscar(new Integer(request.params("id")));
        presupuesto.quitarRepetidos();
        this.formatearObjectForJson(presupuesto);
        String jsonObject = (presupuesto!=null) ? (jsonHelper.convertirAJson(presupuesto)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Presupuesto presupuesto = new Gson().fromJson(request.body(), Presupuesto.class);
        FactoryRepositorio.get(EntidadJuridica.class).modificar(presupuesto);
        response.type("application/json");
        return new JSONObject().put("id", presupuesto.getIdPresupuesto()).toString();
    }

    public String eliminar(Request request, Response response){
        Presupuesto presupuesto = FactoryRepositorio.get(Presupuesto.class).buscar(new Integer(request.params("id")));
        presupuesto.darBaja();
        FactoryRepositorio.get(EntidadJuridica.class).modificar(presupuesto);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        int idEntidadJuridica = new Integer(request.params("idEntJur"));
        List<Presupuesto> presupuestos = new ArrayList<>();
        List<Egreso> egresos = FactoryRepositorio.get(Egreso.class).buscarTodos();
        egresos = egresos.stream().filter(x -> x.getEntidadJuridica().getIdEntidadJuridica() == idEntidadJuridica).collect(Collectors.toList());
        for(Egreso egreso:egresos){
            egreso.quitarRepetidos();
            for(Presupuesto presupuesto: egreso.getListaPresupuestos()){
                presupuesto.quitarRepetidos();
            }
            presupuestos.addAll(egreso.getListaPresupuestos());
        }

        for (Presupuesto presupuesto:presupuestos){
            presupuesto.quitarRepetidos();
        }
        String result = new JSONObject().toString();
        response.type("application/json");
        if (! presupuestos.isEmpty()){
            presupuestos.stream().forEach(x-> this.formatearObjectForJson(x));
            result = jsonHelper.convertirAJson(presupuestos);
        }
        return result;
    }

    private void crearRelaciones(Presupuesto presupuesto) {
        presupuesto.getItems().stream().forEach(x->{
            Item item = FactoryRepositorio.get(Item.class).buscar(x.idItem);
            PresupuestoXItem presupuestoXItem = new PresupuestoXItem();
            presupuestoXItem.setItem(item);
            presupuestoXItem.setCantidad(x.cantidad);
            presupuestoXItem.setValorUnitario(x.valorUnitario);
            presupuestoXItem.setPresupuesto(presupuesto);
            FactoryRepositorio.get(PresupuestoXItem.class).agregar(presupuestoXItem);
        });
        FactoryRepositorio.get(Presupuesto.class).modificar(presupuesto);
    }

    private void formatearObjectForJson(Presupuesto presupuesto) {
        presupuesto.getListaPresupuestoXItem().stream().forEach(x->{
            RequestItemXPresupuesto requestItemXPresupuesto = new RequestItemXPresupuesto();
            requestItemXPresupuesto.cantidad = x.getCantidad();
            requestItemXPresupuesto.valorUnitario = x.getValorUnitario();
            requestItemXPresupuesto.idItem = x.getItem().getIdItem();
            requestItemXPresupuesto.descripcion = x.getItem().getDescripcion();
            presupuesto.getItems().add(requestItemXPresupuesto);
        });
    }
}