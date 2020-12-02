package domain.controllers;

import com.google.gson.Gson;
import domain.entities.Models.BandejaMensaje.Mensaje;
import domain.entities.Models.ContextAPI.*;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Usuarios.Usuario;
import domain.entities.Services.EncryptorAesGcmPassword;
import domain.repositories.factories.FactoryRepositorio;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class LoginController {

    private String secretKey = "$Gesoc2020$Gesoc2020$Gesoc2020G1";
    private String salt = "g4atos";
    JSONHelper jsonHelper = new JSONHelper();

    public String autenticarUsuario(Request request, Response response){
        RequestLogin requestLogin = new Gson().fromJson(request.body(), RequestLogin.class);
        response.type("application/json");
        List<Usuario> usuarios = FactoryRepositorio.get(Usuario.class).buscarTodos();
        List<Usuario> usuariosEncontrados = usuarios.stream().filter((x->(x.getNombreUsuario().equals(requestLogin.user)))).collect(Collectors.toList());
        ResponseLogin responseLogin = new ResponseLogin();
        if(usuariosEncontrados.stream().count() == 0){
            responseLogin.status = false;
            return jsonHelper.convertirAJson(responseLogin);
        }
        Usuario usuarioEncontrado = usuariosEncontrados.get(0);
        if(usuarioEncontrado == null){
            responseLogin.status = false;
            return jsonHelper.convertirAJson(responseLogin);
        }
        try {
            String claveDesencriptada = usuarioEncontrado.getClave();
            String claveRequestDesencriptada = requestLogin.pass;
            if(claveDesencriptada.equals(claveRequestDesencriptada)){
                responseLogin.user = usuarioEncontrado.getNombreUsuario();
                responseLogin.status = true;
                responseLogin.role = usuarioEncontrado.getRol().getDescripcion();
                responseLogin.idEntidadJuridica = usuarioEncontrado.getEntidadJuridica().getIdEntidadJuridica();
                return jsonHelper.convertirAJson(responseLogin);
            }else{
                responseLogin.status = false;
                return jsonHelper.convertirAJson(responseLogin);
            }
        }catch (Exception ex){
            responseLogin.status = false;
            return jsonHelper.convertirAJson(responseLogin);
        }
    }


    public String encrypt(String strToEncrypt, String secret)
    {
        try
        {
//            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//            IvParameterSpec ivspec = new IvParameterSpec(iv);
//
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
//            SecretKey tmp = factory.generateSecret(spec);
//            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
//            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decrypt(String strToDecrypt, String secret) {
        try
        {

//            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//            IvParameterSpec ivspec = new IvParameterSpec(iv);
//
//            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
//            SecretKey tmp = factory.generateSecret(spec);
//            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
//            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}
