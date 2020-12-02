package domain.entities.Validadores.AutenticadorContrasenas;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Collections;
import java.util.List;

public class AutenticadorContrasenas {

    public static final String PASSWORD_REGEX = "^(?!.*(.)\\1{3})(?=(.*[\\d]){1,})(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[@#$%!_-]){1,})(?:[\\da-zA-Z@#$%!_-]){8,100}$"; // https://regexr.com/3bguv
    public static List listaPass = readFileInList("./src/pass.txt");

    public AutenticadorContrasenas(){
    }

    public static List<String> readFileInList(String fileName){
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return lines;
    }

    public static boolean check(String password){
        return (AutenticadorContrasenas.checkRegex(password) && AutenticadorContrasenas.checkFile(password));
    }


    public static boolean checkRegex(String password){
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean checkFile(String password){
        return (!listaPass.contains(password));
    }

    public static byte[] hashClave(String usuario, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = usuario.getBytes();

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        return hash;
    }

    public static String toHex(byte[] array) throws NoSuchAlgorithmException
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }





}
