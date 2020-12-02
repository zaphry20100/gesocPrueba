package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.ContextAPI.RequestEgresoPresupuestos;
import domain.entities.Models.Transacciones.*;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

public class EgresosPresupuestosController {
    public String crearConPresupuestos(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        RequestEgresoPresupuestos egresoPresupuestos = new Gson().fromJson(request.body(), RequestEgresoPresupuestos.class);

        FactoryRepositorio.get(Egreso.class).agregar(egresoPresupuestos.egreso);
        this.crearRelaciones(egresoPresupuestos);
        response.type("application/json");
        return new JSONObject().put("id", egresoPresupuestos.egreso.getIdEgreso()).toString();
    }

    private void crearRelaciones(RequestEgresoPresupuestos egresoPresupuestos){

//        Presupuesto presupuesto = new Presupuesto();
        egresoPresupuestos.presupuestos.lista.stream().forEach(x->{
                x.setEgresoAsignado(egresoPresupuestos.egreso);
                x.setEgreso(egresoPresupuestos.egreso);
                FactoryRepositorio.get(Proveedor.class).agregar(x);
/*            }else{
                Presupuesto presupuesto = FactoryRepositorio.get(Presupuesto.class).buscar(x.getIdPresupuesto());
                presupuesto.getListaPresupuestoXItem().stream().forEach(y->{
                    Egresoxitem egresoxitem = new Egresoxitem();
                    egresoxitem.setValorUnitario(y.getValorUnitario());
                    egresoxitem.setCantidad(y.getCantidad());
                    egresoxitem.setItem(y.getItem());
                    egresoxitem.setEgreso(egresoPresupuestos.egreso);
                    FactoryRepositorio.get(Egresoxitem.class).agregar(egresoxitem);
                });
                FactoryRepositorio.get(Egreso.class).modificar(egresoPresupuestos.egreso);
            }*/

        });

        egresoPresupuestos.egreso.setListaPresupuestos(egresoPresupuestos.presupuestos.lista);
        egresoPresupuestos.egreso.setPresupuestoElegido(egresoPresupuestos.presupuestos.lista.get(egresoPresupuestos.presupuestos.presupuestoElegido));


        // --------------- MODIFICAR ------------------
        FactoryRepositorio.get(Egreso.class).modificar(egresoPresupuestos.egreso);
    }



}
