package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.BandejaMensaje.BandejaMensaje;
import domain.entities.Models.BandejaMensaje.Mensaje;
import domain.entities.Models.ContextAPI.RequestLogin;
import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Ingreso;
import domain.entities.Models.Transacciones.Presupuesto;
import domain.entities.Models.Transacciones.Proveedor;
import domain.entities.Models.Usuarios.Usuario;
import domain.entities.Services.EncryptorAesGcmPassword;
import domain.entities.Validadores.AutenticadorContrasenas.AutenticadorContrasenas;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRestController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        response.type("application/json");
        Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
        FactoryRepositorio.get(Usuario.class).agregar(usuario);
        this.crearRelaciones(usuario, new Integer(request.params("idEntJur")));
        return new JSONObject().put("id", usuario.getIdUsuario()).toString();

        //        response.type("application/json");
//        RequestLogin requestLogin = new Gson().fromJson(request.body(), RequestLogin.class);
//        if (AutenticadorContrasenas.check(requestLogin.clave)){
//            Usuario usuario = new Usuario();
//            try {
//                usuario.setClave(AutenticadorContrasenas.hashClave(requestLogin.usuario,requestLogin.clave));
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            } catch (InvalidKeySpecException e) {
//                e.printStackTrace();
//            }
//            FactoryRepositorio.get(Usuario.class).agregar(usuario);
//            this.crearRelaciones(usuario, new Integer(request.params("idEntJur")));
//            return new JSONObject().put("id", usuario.getIdUsuario()).toString();
//        }else{
//            return "Contraseña no cumple los requisitos - Debe tener al menos 8 caracteres, 1 simbolo, 1 numero, y 1 mayuscula";
//        }
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(Usuario.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        Usuario usuario = FactoryRepositorio.get(Usuario.class).buscar(new Integer(request.params("id")));
        usuario = this.quitarRepetidos(usuario);
        String jsonObject = (usuario!=null) ? (jsonHelper.convertirAJson(usuario)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        Usuario usuario = new Gson().fromJson(request.body(), Usuario.class);
        FactoryRepositorio.get(Usuario.class).modificar(usuario);
        response.type("application/json");
        return new JSONObject().put("id", usuario.getIdUsuario()).toString();
    }

    public String eliminar(Request request, Response response){
        Usuario usuario = FactoryRepositorio.get(Usuario.class).buscar(new Integer(request.params("id")));
        usuario.darBaja();
        FactoryRepositorio.get(Usuario.class).modificar(usuario);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        int idEntidadJuridica = new Integer(request.params("idEntJur"));
        List<Usuario> usuarios = FactoryRepositorio.get(Usuario.class).buscarTodos();
        usuarios = usuarios.stream().filter(x -> x.getEntidadJuridica().getIdEntidadJuridica() == idEntidadJuridica).collect(Collectors.toList());
        String result = new JSONObject().toString();
        for(Usuario usuario:usuarios){
            usuario = this.quitarRepetidos(usuario);
        }
        response.type("application/json");
        if (! usuarios.isEmpty()){
            result = jsonHelper.convertirAJson(usuarios);
        }
        return result;
    }

    private Usuario quitarRepetidos(Usuario usuario){
        usuario.setEgresos(usuario.getEgresos().stream().distinct().collect(Collectors.toList()));
        usuario.setRevisiones(usuario.getRevisiones().stream().distinct().collect(Collectors.toList()));
        usuario.getBandejaMensaje().quitarRepetidos();
        return usuario;
    }

    private void crearRelaciones(Usuario user, int idEntidadJuridica){

        // Bandeja de mensaje
        BandejaMensaje bandejaMensaje = new BandejaMensaje().setUsuario(user);
        user.setBandejaMensaje(bandejaMensaje);
        FactoryRepositorio.get(BandejaMensaje.class).agregar(bandejaMensaje);

        // Entidad Juridica
        user.setEntidadJuridica(FactoryRepositorio.get(EntidadJuridica.class).buscar(idEntidadJuridica));

        // --------------- MODIFICAR ------------------ //
        FactoryRepositorio.get(Usuario.class).modificar(user);

    }

}