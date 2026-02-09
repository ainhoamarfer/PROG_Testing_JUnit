package ejercicio01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NormalizadorNombreCompletoTest {

    /*
    Devuelve el mismo resultado para un nombre ya normalizado.
    Dar: string valido
    recibir: string valido
     */
    @Test
    public void Test_Normalize_NombreValido_MismoNombreValido() {

        NormalizadorNombreCompleto normalizador = new NormalizadorNombreCompleto();
        String nombreCompleto = "Ainhoa Martinez";
        String nombreRevisado = normalizador.normalize(nombreCompleto);

        assertEquals(nombreCompleto, nombreRevisado);
    }

    /*
    Recorta espacios al inicio y al final.
    Dar: string invalido con espacios al inicio y al final.
    recibir: string valido
     */
    @Test
    public void Test_Normalize_EspaciosPrincipioFinal_NombresSinEspacios() {

        NormalizadorNombreCompleto normalizador = new NormalizadorNombreCompleto();
        String valorEntrada = " Ainhoa Martinez ";
        String valorEsperado = "Ainhoa Martinez";
        String nombreRevisado = normalizador.normalize(valorEntrada);

        assertEquals(valorEsperado, nombreRevisado);
    }

    /*
    Colapsa múltiples espacios internos.
    Dar: string con espacios de más
    recibir: string valido
     */
    @Test
    public void Test_Normalize_EspaciosInternos_NombreSinEspacios() {

        NormalizadorNombreCompleto normalizador = new NormalizadorNombreCompleto();
        String nombreinvalido = "Ainhoa   Martinez";
        String nombreCorrecto = "Ainhoa Martinez";
        String nombreRevisado = normalizador.normalize(nombreinvalido);

        assertEquals(nombreCorrecto, nombreRevisado);
    }

    /*
   Convierte "mARiA jOSE" a "Maria Jose".
   Dar: string invalido con mayus como minus y viceversa
   recibir: string valido
    */
    @Test
    public void Test_Normalize_CaracteresMayusEnMinus_NombreValidos() {

        NormalizadorNombreCompleto normalizador = new NormalizadorNombreCompleto();
        String nombreinvalido = "mARiA jOSE";
        String nombreCorrecto = "Maria Jose";
        String nombreRevisado = normalizador.normalize(nombreinvalido);

        assertEquals(nombreCorrecto, nombreRevisado);
    }

    /*
  Mantiene palabras con una sola letra ("a b" → "A B").
  Dar: string valido con uno de los nombres es solo una letra
  recibir: string valido
   */
    @Test
    public void Test_Normalize_NombreConUnaSolaLetraValido_NombreConUnaSolaLetraValido() {

        NormalizadorNombreCompleto normalizador = new NormalizadorNombreCompleto();
        String nombreValorEntrada = "Maria A";
        String nombreCorrecto = "Maria A";
        String nombreRevisado = normalizador.normalize(nombreValorEntrada);

        assertEquals(nombreCorrecto, nombreRevisado);
    }

    /*
    Lanza excepción con null.
    Dar: String null
    recibir: IllegalArgumentException
     */
    @Test
    public void test_normalize_entradaNull_lanzaIllegalArgException() {

        try{
            new NormalizadorNombreCompleto().normalize(null);
            fail();

        }catch(IllegalArgumentException e){ //nunca poner exception generica
            assertTrue(true);//si salta es que ha salido bien
        }

        /*
        esta es otra forma de hacer lo mismo

        assertThrows(IllegalArgumentException.class, ()->{new NormalizadorNombreCompleto().normalize(null);});
         */
    }

    /*
   Lanza excepción con " " (solo espacios).
   Dar: String vacia
   recibir: IllegalArgumentException
    */
    @Test
    public void test_normalize_entradaVacia_lanzaIllegalArgException() {

        try{
            new NormalizadorNombreCompleto().normalize("");
            fail();

        }catch(IllegalArgumentException e){ //nunca poner exception generica
            assertTrue(true);//si salta es que ha salido bien
        }
    }


}
