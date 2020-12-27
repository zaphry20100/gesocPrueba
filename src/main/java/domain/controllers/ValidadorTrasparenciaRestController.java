package domain.controllers;

import domain.entities.Models.BandejaMensaje.ServicioMensajes;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Validadores.ValidadorTransparencia.ValidadorTransparencia;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fj.function.Booleans.and;

public class ValidadorTrasparenciaRestController {

    JSONHelper jsonHelper = new JSONHelper();

    private ValidadorTransparencia validadorTransparencia;


    public String ejecutarValidacion(Request request, Response response){

        if(validadorTransparencia==null){
            validadorTransparencia = new ValidadorTransparencia();
        }

        List<Egreso> egresos = new ArrayList<>();
        EntidadJuridica entidadJuridica;
        if(request.params("id") != null){
            Egreso egreso = FactoryRepositorio.get(Egreso.class).buscar(new Integer(request.params("id")));
            egresos.add(egreso);
            validadorTransparencia.setConfig(egreso.getEntidadJuridica().getConfiguracionEntidadJuridica());
            entidadJuridica = egreso.getEntidadJuridica();
        }else{
            entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));
            entidadJuridica.setEgresos(entidadJuridica.getTodosLosEgresos().stream().distinct().collect(Collectors.toList()));
            egresos = entidadJuridica.getTodosLosEgresos();
            validadorTransparencia.setConfig(entidadJuridica.getConfiguracionEntidadJuridica());
        }
        List<Egreso> egresoNoValidados = egresos.stream().filter(x ->  ! x.isValidado() ).collect(Collectors.toList());
        System.out.println("Ent Jur a validar: " + entidadJuridica.getIdEntidadJuridica());
        System.out.println("Cant egresos a validar: "+egresoNoValidados.size());

        egresoNoValidados.forEach(x-> {
            System.out.println("EGreso: " + x.getIdEgreso());
            x.quitarRepetidos();
            String mensaje = validadorTransparencia.validar(x);

            //String mensaje = "asd";
            //x.setValidado(true);

            //FactoryRepositorio.get(EntidadJuridica.class).modificar(x);
            ServicioMensajes.mandarMensaje(mensaje, x.getRevisores());
        });

        response.type("application/json");
        return this.calcularEstadisticaTransparencia(entidadJuridica);
    }

    public String mostrarEstado(Request request, Response response){
        response.type("application/json");
        EntidadJuridica entidadJuridica = FactoryRepositorio.get(EntidadJuridica.class).buscar(new Integer(request.params("idEntJur")));
        entidadJuridica.setEgresos(entidadJuridica.getTodosLosEgresos().stream().distinct().collect(Collectors.toList()));
        entidadJuridica.quitarRepetidos();
        return this.calcularEstadisticaTransparencia(entidadJuridica);
    }

    private String calcularEstadisticaTransparencia(EntidadJuridica entidadJuridica){
        List<Egreso> egresos = entidadJuridica.getTodosLosEgresos();
        List<Egreso> egresosNoValidado = egresos.stream().filter(x -> ! x.isValidado() ).collect(Collectors.toList());

        System.out.println("Egresos totales: "+ egresos.size());
        System.out.println("egresosNoValidado: "+ egresosNoValidado.size());

        int egresosTotales = egresos.size();
        int egresosNoValidadosTotales = egresosNoValidado.size();
        int egresosValidados = egresosTotales - egresosNoValidadosTotales;

        JSONObject resultado = new JSONObject();
        resultado.put("egresosNoValidadosTotales", egresosNoValidadosTotales);
        resultado.put("egresosValidados", egresosValidados);

        return resultado.toString();
    }

}
