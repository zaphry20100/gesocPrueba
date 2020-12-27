package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Categorias.EgresoXCategoria;
import domain.entities.Models.Categorias.PresupuestoXCategoria;
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
        System.out.println(request.body());
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

        List<Presupuesto> presupuestos = FactoryRepositorio.get(Presupuesto.class).buscarTodos();
        //List<Presupuesto> presupuestos = FactoryRepositorio.get(Presupuesto.class).buscarTodos(idEntidadJuridica);

        //presupuestos = presupuestos.stream().filter(x -> x.getEntidadJuridica().getIdEntidadJuridica() == idEntidadJuridica).collect(Collectors.toList());

        presupuestos.stream().forEach(x-> x.quitarRepetidos());
        presupuestos = presupuestos.stream().distinct().collect(Collectors.toList());


        List<Presupuesto> presupuestosFiltrados = new ArrayList<>();

        presupuestos.stream().forEach(x->{
            int idEntJur = x.getEntidadJuridica().getIdEntidadJuridica();
            if(idEntJur == idEntidadJuridica){
                presupuestosFiltrados.add(x);
            }

        });

        response.type("application/json");
        String result = new JSONObject().toString();
        if (! presupuestosFiltrados.isEmpty()){
            result = jsonHelper.convertirListaAJson(presupuestosFiltrados);
        }
        return result;
    }

    private void agregarCategoriaPresupuesto(Presupuesto presupuesto){
        presupuesto.getIdsCategorias().forEach(x -> {
            PresupuestoXCategoria presupuestoXCategoria = new PresupuestoXCategoria();
            presupuestoXCategoria.setCategoriaPresupuesto(FactoryRepositorio.get(CategoriaPresupuesto.class).buscar(x));
            presupuestoXCategoria.setPresupuesto(presupuesto);
            FactoryRepositorio.get(PresupuestoXCategoria.class).agregar(presupuestoXCategoria);
        });
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

        agregarCategoriaPresupuesto(presupuesto);

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