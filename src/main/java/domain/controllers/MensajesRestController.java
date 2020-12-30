package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.BandejaMensaje.BandejaMensaje;
import domain.entities.Models.BandejaMensaje.Mensaje;
import domain.entities.Models.ContextAPI.ResponseMensajes;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Ingreso;
import domain.entities.Models.Transacciones.Presupuesto;
import domain.entities.Models.Transacciones.Proveedor;
import domain.entities.Models.Usuarios.Usuario;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MensajesRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        Mensaje mensaje = new Gson().fromJson(request.body(), Mensaje.class);
        FactoryRepositorio.get(Proveedor.class).agregar(mensaje);
        response.type("application/json");
        return new JSONObject().put("id", mensaje.getIdMensaje()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Mensaje.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Mensaje mensaje = FactoryRepositorio.get(Mensaje.class).buscar(new Integer(request.params("id")));
        String jsonObject = (mensaje!=null) ? (jsonHelper.convertirAJson(mensaje)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Mensaje mensaje = new Gson().fromJson(request.body(), Mensaje.class);
        FactoryRepositorio.get(Mensaje.class).modificar(mensaje);
        response.type("application/json");
        return new JSONObject().put("id", mensaje.getIdMensaje()).toString();
    }

    public String eliminar(Request request, Response response){
        Mensaje mensaje = FactoryRepositorio.get(Mensaje.class).buscar(new Integer(request.params("id")));
        mensaje.darBaja();
        FactoryRepositorio.get(Mensaje.class).modificar(mensaje);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<Mensaje> presupuestos = FactoryRepositorio.get(Mensaje.class).buscarTodos();
        String result = new JSONObject().toString();
        response.type("application/json");
        if (! presupuestos.isEmpty()){
            result = jsonHelper.convertirAJson(presupuestos);
        }
        return result;
    }

    public String marcarLeidos(Request request, Response response) {

        Usuario usuario = FactoryRepositorio.get(Usuario.class).buscar(new Integer(request.params("idUser")));

        List<Mensaje> mensajesList = FactoryRepositorio.get(Mensaje.class).buscarTodos();
        mensajesList = mensajesList.stream().filter(x->x.getBandejamensaje().getIdbandejamensaje() == usuario.getBandejaMensaje().getIdbandejamensaje()).collect(Collectors.toList());

        mensajesList.forEach(y-> {
            y.setLeido(true);
            FactoryRepositorio.get(Mensaje.class).modificar(y);
        });

        return new JSONObject().toString();

    }

    public String mostrarMensajes(Request request, Response response) {

        Usuario usuario = FactoryRepositorio.get(Usuario.class).buscar(new Integer(request.params("idUser")));

        ResponseMensajes responseMensajes = new ResponseMensajes();
        List<Mensaje> mensajesList = FactoryRepositorio.get(Mensaje.class).buscarTodos();
        List<Mensaje> msjs = new ArrayList<>();

        if(Objects.nonNull(mensajesList)) {
            mensajesList.stream().forEach(x->{
                if(x.getBandejamensaje().getIdbandejamensaje() == usuario.getBandejaMensaje().getIdbandejamensaje()){
                    msjs.add(x);
                }
            });
        }

        responseMensajes.mensajes = msjs;
        responseMensajes.cantidadMensajesNuevos = mensajesList.stream().filter(x-> !x.isLeido() ).collect(Collectors.toList()).size();

        String result = new JSONObject().toString();

        response.type("application/json");
        if (! responseMensajes.mensajes.isEmpty()){
            result = jsonHelper.convertirAJson(responseMensajes);
        }

        return result;

    }

    public String crearMensajes(Request request, Response response) {
        Mensaje mensaje = new Gson().fromJson(request.body(), Mensaje.class);
        FactoryRepositorio.get(Proveedor.class).agregar(mensaje);
        Usuario usuario = FactoryRepositorio.get(Usuario.class).buscar(new Integer(request.params("idUser")));
        usuario.getBandejaMensaje().agregarMensajes(mensaje);
        FactoryRepositorio.get(Usuario.class).modificar(usuario);
        return new JSONObject().toString();
    }

}