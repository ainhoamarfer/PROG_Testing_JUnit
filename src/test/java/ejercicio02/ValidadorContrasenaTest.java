package ejercicio02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidadorContrasenaTest {

    /*
    Acepta una contraseña que cumple todas las reglas.
     */

    @Test
    public void test_isValid_entradaValida_salidaValida() {

        ValidadorContrasena validador = new ValidadorContrasena();
        String entradaValida = "123456Aa.";
        boolean salidaValidaTrue = validador.isValid(entradaValida);

        assertTrue(salidaValidaTrue);
    }

    /*
    Rechaza por longitud insuficiente.
     */

    @Test
    public void test_isValid_entradaDemasiadoCorta_salidaInvalidaFalse() {

        ValidadorContrasena validador = new ValidadorContrasena();
        String entradaInvalida = "1234Aa.";
        boolean salidaInvalidaFalse = validador.isValid(entradaInvalida);

        assertFalse(salidaInvalidaFalse);
    }


    /*
    Rechaza si falta mayúscula.
     */

    @Test
    public void test_isValid_entradaSinMayus_salidaInvalidaFalse() {

        ValidadorContrasena validador = new ValidadorContrasena();
        String entradaInvalida = "123456aa.";
        boolean salidaInvalidaFalse = validador.isValid(entradaInvalida);

        assertFalse(salidaInvalidaFalse);
    }

    /*
    Rechaza si falta dígito.
     */

    @Test
    public void test_isValid_entradaSinDigit_salidaInvalidaFalse() {

        ValidadorContrasena validador = new ValidadorContrasena();
        String entradaInvalida = "12345678";
        boolean salidaInvalidaFalse = validador.isValid(entradaInvalida);

        assertFalse(salidaInvalidaFalse);
    }

    /*
    Rechaza si falta símbolo.
     */

    @Test
    public void test_isValid_entradaSinSimb_salidaInvalidaFalse() {

        ValidadorContrasena validador = new ValidadorContrasena();
        String entradaInvalida = "123456aaA";
        boolean salidaInvalidaFalse = validador.isValid(entradaInvalida);

        assertFalse(salidaInvalidaFalse);
    }

    /*
    Caso borde: exactamente 8 caracteres y cumple reglas.
     */

    @Test
    public void test_isValid_entradaLimite_salidaValidaTrue() {

        ValidadorContrasena validador = new ValidadorContrasena();
        String entradValida = "12345.aA";
        boolean salidaValidaTrue = validador.isValid(entradValida);

        assertTrue(salidaValidaTrue);
    }

    /*
    Caso con espacios.
    */

    @Test
    public void test_isValid_entradaInvalidaConEspacios_salidaInvalidaFalse() {

        ValidadorContrasena validador = new ValidadorContrasena();
        String entradaInvalida = "123456 .aA";
        boolean salidaInvalidaFalse = validador.isValid(entradaInvalida);

        assertFalse(salidaInvalidaFalse);
    }
}
