package domain.controllers;

import domain.entities.Models.Transacciones.DocumentoComercial;
import domain.entities.Models.Transacciones.Egreso;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class UploadFilesController {
    JSONHelper jsonHelper = new JSONHelper();

    public String uploadFile(Request request, Response response){

        String nombreArchivo = request.params("nombreArchivo");
        int idEgreso = new Integer(request.params("idEgreso"));
        String tipoArchivo = request.params("tipoArchivo");

        DocumentoComercial documentoComercial = new DocumentoComercial();
        documentoComercial.setEgreso(FactoryRepositorio.get(Egreso.class).buscar(idEgreso));
        documentoComercial.setNumeroDocCom(0);
        documentoComercial.setPath("./" + nombreArchivo + "." + tipoArchivo);
        documentoComercial.setTipo(tipoArchivo);

        FactoryRepositorio.get(DocumentoComercial.class).agregar(documentoComercial);

        String location = "./";  // the directory location where files will be stored
        long maxFileSize = 100000000;  // the maximum size allowed for uploaded files
        long maxRequestSize = 100000000;  // the maximum size allowed for multipart/form-data requests
        int fileSizeThreshold = 1024;  // the size threshold after which files will be written to disk
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
        request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);

        Collection<Part> parts = null;
        try {
            parts = request.raw().getParts();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        String fName = null;
        Part uploadedFile = null;

        for(Part part : parts) {
            fName = part.getSubmittedFileName();
            uploadedFile = part;

            Path out = Paths.get("./" + nombreArchivo + "." + tipoArchivo);

            try (final InputStream in = uploadedFile.getInputStream()) {
                Files.copy(in, out);
                uploadedFile.delete();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // cleanup
        multipartConfigElement = null;
        parts = null;
        uploadedFile = null;

        String jsonObject = (documentoComercial!=null) ? (jsonHelper.convertirAJson(documentoComercial)):(new JSONObject().toString());


        return jsonObject;
    }

    public Response downloadFile(Request request, Response response) {

        int idDocComercial = new Integer(request.params("idDocCom"));
        DocumentoComercial docComercial = FactoryRepositorio.get(DocumentoComercial.class).buscar(idDocComercial);

        String[] filename = docComercial.getPath().split("/");


        response.header("Content-disposition", "attachment; filename=;" + filename[filename.length - 1]);

        File file = new File(docComercial.getPath());
        OutputStream outputStream = null;
        try {
            outputStream = response.raw().getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.write(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
