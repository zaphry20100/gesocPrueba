package miTest;
import static fj.data.List.fromString;
import domain.entities.Validadores.AutenticadorContrasenas.AutenticadorContrasenas;
import org.junit.Assert;
import org.junit.Test;

public class AutenticadorContrasenasTester {
    String passwordToTest;
    @Test
    public void PasswordConFormatoCorrecto() {
        passwordToTest = "Disenopass$1998";
        Assert.assertTrue(AutenticadorContrasenas.check(passwordToTest));
    }
    @Test
    public void PasswordSinMayuscula() {
        passwordToTest = "disenopass$1234";
        Assert.assertFalse(AutenticadorContrasenas.check(passwordToTest));
    }
    @Test
    public void PasswordSinSimboloEspecial() {
        passwordToTest = "Disenopass1234";
        Assert.assertFalse(AutenticadorContrasenas.check(passwordToTest));
    }
    @Test
    public void PasswordSinNumero() {
        passwordToTest = "Disenopass$";
        Assert.assertFalse(AutenticadorContrasenas.check(passwordToTest));
    }
    @Test
    public void PasswordSinMinuscula(){
        passwordToTest = "DISENOPASS$13";
        Assert.assertFalse(AutenticadorContrasenas.check((passwordToTest)));
    }
    @Test
    public void PasswordConCuatroCaracteresConsecutivos(){
        passwordToTest = "Disenopassss$111";
        Assert.assertFalse(AutenticadorContrasenas.check(passwordToTest));
    }
    @Test
    public void PasswordConCuatroNumerosConsecutivos(){
        passwordToTest = "Diseñopass$1111";
        Assert.assertFalse(AutenticadorContrasenas.check(passwordToTest));
    }
}
