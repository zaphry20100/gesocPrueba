package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.DocumentoComercial;
import domain.entities.Models.Transacciones.Ingreso;
import domain.entities.Models.Transacciones.Proveedor;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.util.List;

public class DocumentoComercialController {

    JSONHelper jsonHelper = new JSONHelper();

    public String crear(Request request, Response response){
        DocumentoComercial docCom = new Gson().fromJson(request.body(), DocumentoComercial.class);
        FactoryRepositorio.get(DocumentoComercial.class).agregar(docCom);
        response.type("application/json");
        return new JSONObject().put("id", docCom.getIdDocumentoComercial()).toString();
    }

    public String existe(Request request, Response response){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Exists", FactoryRepositorio.get(DocumentoComercial.class).existe(new Integer(request.params("id"))));
        response.type("application/json");
        return jsonObject.toString();
    }

    public String mostrar(Request request, Response response){
        DocumentoComercial docCom = FactoryRepositorio.get(DocumentoComercial.class).buscar(new Integer(request.params("id")));
        String jsonObject = (docCom!=null) ? (jsonHelper.convertirAJson(docCom)):(new JSONObject().toString());
        response.type("application/json");
        return jsonObject;
    }

    public String modificar(Request request, Response response){
        DocumentoComercial docCom = new Gson().fromJson(request.body(), DocumentoComercial.class);
        FactoryRepositorio.get(EntidadJuridica.class).modificar(docCom);
        response.type("application/json");
        return new JSONObject().put("id", docCom.getIdDocumentoComercial()).toString();
    }

    public String eliminar(Request request, Response response){
        DocumentoComercial docCom = FactoryRepositorio.get(DocumentoComercial.class).buscar(new Integer(request.params("id")));
        docCom.darBaja();
        FactoryRepositorio.get(DocumentoComercial.class).modificar(docCom);
        response.type("application/json");
        return new JSONObject().toString();
    }

    public String mostrarTodos(Request request, Response response) {
        List<DocumentoComercial> docComs = FactoryRepositorio.get(DocumentoComercial.class).buscarTodos();
        String result = new JSONObject().toString();
        if (! docComs.isEmpty()){
            result = jsonHelper.convertirAJson(docComs);
        }
        response.type("application/json");
        return result;
    }

}