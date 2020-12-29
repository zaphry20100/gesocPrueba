package domain.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.EgresoXIngreso;
import domain.entities.Models.Transacciones.Ingreso;
import domain.entities.Services.VinculadorService;
import domain.entities.VinculacionOperaciones.Models.*;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VinculadorController {

    JSONHelper jsonHelper = new JSONHelper();

    public String mostrar(Request request, Response response){
        List<NombreCriterio> criteriosVinculador = new ArrayList<>();
        try{
            criteriosVinculador = VinculadorService.instancia().mostrarCriterios();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return jsonHelper.convertirListaAJson(criteriosVinculador);
    }

    public String consultarEstado(Request request, Response response){
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));

        entidadJuridica.setListaIngresos(entidadJuridica.getListaIngresos().stream().distinct().collect(Collectors.toList()));
        entidadJuridica.setEgresos(entidadJuridica.getTodosLosEgresos().stream().distinct().collect(Collectors.toList()));

        int operacionesNoVinculadas = entidadJuridica.getTodosLosEgresos().stream().filter(x -> x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size()
                + entidadJuridica.getListaIngresos().stream().filter(x -> x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size();

        int operacionesVinculadas = entidadJuridica.getListaIngresos().stream().filter(x -> !x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size()
                +entidadJuridica.getTodosLosEgresos().stream().filter(x -> !x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size();

        JSONObject resultado = new JSONObject();
        resultado.put("operacionesNoVinculadas", operacionesNoVinculadas);
        resultado.put("operacionesVinculadas", operacionesVinculadas);

        return resultado.toString();
    }

    public String vincular(Request request, Response response){

        Type listType = new TypeToken<ArrayList<CriterioVinculacion>>(){}.getType();
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));
        entidadJuridica.setListaIngresos(entidadJuridica.getListaIngresos().stream().distinct().collect(Collectors.toList()));
        entidadJuridica.setEgresos(entidadJuridica.getTodosLosEgresos().stream().distinct().collect(Collectors.toList()));

        List<CriterioVinculacion> criteriosVinculacion = new Gson().fromJson(request.body(), listType);

        System.out.println(criteriosVinculacion.size());
        List<EgresoEntrada> egresos = new ArrayList<>();
        List<IngresoEntrada> ingresos = new ArrayList<>();

        entidadJuridica.getTodosLosEgresos().stream().filter(x -> x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).forEach(x -> egresos.add(new EgresoEntrada((x))));
        entidadJuridica.getListaIngresos().stream().filter(x -> x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).forEach(y -> ingresos.add(new IngresoEntrada(y)));

        //FactoryRepositorio.get(Egreso.class).buscarTodos().stream().collect(Collectors.toList()).forEach(x -> egresos.add(new EgresoEntrada((x))));
        //FactoryRepositorio.get(Ingreso.class).buscarTodos().stream().collect(Collectors.toList()).forEach(y -> ingresos.add(new IngresoEntrada(y)));

        OperacionEntrada operacionEntrada = new OperacionEntrada(criteriosVinculacion, ingresos, egresos);

        try {
            ejecutar(operacionEntrada);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.type("application/json");

        entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));
        entidadJuridica.setListaIngresos(entidadJuridica.getListaIngresos().stream().distinct().collect(Collectors.toList()));
        entidadJuridica.setEgresos(entidadJuridica.getTodosLosEgresos().stream().distinct().collect(Collectors.toList()));

        int operacionesNoVinculadas = entidadJuridica.getTodosLosEgresos().stream().filter(x -> x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size()
                                    + entidadJuridica.getListaIngresos().stream().filter(x -> x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size();

        int operacionesVinculadas = entidadJuridica.getTodosLosEgresos().stream().filter(x -> !x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size()
                                    + entidadJuridica.getListaIngresos().stream().filter(x -> !x.getEgresoXIngreso().isEmpty()).collect(Collectors.toList()).size();

        JSONObject resultado = new JSONObject();
        resultado.put("operacionesNoVinculadas", operacionesNoVinculadas);
        resultado.put("operacionesVinculadas", operacionesVinculadas);

        return resultado.toString();
    }

    private void ejecutar(OperacionEntrada operacionEntrada) throws IOException {
        OperacionSalida operacionSalida = VinculadorService.instancia().ejecutarVinculacion(operacionEntrada);

        operacionSalida.getIngresos().forEach(x -> {
            Ingreso ingreso = FactoryRepositorio.get(Ingreso.class).buscar(x.idIngreso);
            System.out.println(ingreso.getIdIngreso());

            x.egresos.forEach(y->{
                        Egreso egresoParcial = FactoryRepositorio.get(Egreso.class).buscar(y);
                        EgresoXIngreso egresoXIngreso = new EgresoXIngreso();
                        egresoXIngreso.setEgreso(egresoParcial);
                        egresoXIngreso.setIngreso(ingreso);
                        FactoryRepositorio.get(EgresoXIngreso.class).agregar(egresoXIngreso);

                        ingreso.getEgresoXIngreso().add(egresoXIngreso);
                        FactoryRepositorio.get(Ingreso.class).modificar(ingreso);

                        egresoParcial.getEgresoXIngreso().add(egresoXIngreso);
                        FactoryRepositorio.get(Egreso.class).modificar(egresoParcial);

                    }
            );

        });

        operacionSalida.getEgresos().forEach(x -> {
            Egreso egreso = FactoryRepositorio.get(Egreso.class).buscar(x.idEgreso);
            System.out.println(egreso.getIdEgreso());

            x.ingresos.forEach(y->{
                        Ingreso ingresoParcial = FactoryRepositorio.get(Ingreso.class).buscar(y);

                        EgresoXIngreso egresoXIngreso = new EgresoXIngreso();
                        egresoXIngreso.setIngreso(ingresoParcial);
                        egresoXIngreso.setEgreso(egreso);
                        FactoryRepositorio.get(EgresoXIngreso.class).agregar(egresoXIngreso);

                        ingresoParcial.getEgresoXIngreso().add(egresoXIngreso);
                        FactoryRepositorio.get(Ingreso.class).modificar(ingresoParcial);

                        egreso.getEgresoXIngreso().add(egresoXIngreso);
                        FactoryRepositorio.get(Egreso.class).modificar(egreso);

                    }
            );
        });

        System.out.println(operacionSalida.toString());
    }
}
