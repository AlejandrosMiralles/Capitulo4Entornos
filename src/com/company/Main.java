package com.company;
import java.util.Scanner;

/**
 * Clase que calcula todos los números primos hasta un número proporcionado por el usuario siguiendo la metodología de Erastótenes
 */
public class Main {
    
    /**
     * Método principal. Es la encargada de mostrar los números primos
     */
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        int limite=teclado.nextInt();
        int vector[] =new int[limite];
        System.out.println("\nVector inicial hasta :"+limite);
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(i+1+"\t");
        }

        vector=generarPrimos(limite);
        System.out.println("\nVector de primos hasta:"+limite);
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(vector[i]+"\t");
        }

        teclado.close();
    }

    /**
     * Genera todos los números primos hasta cierto número
     * @param max Límite del método. No calculará números primos superiores a este
     * @return Un array con todos los números primos calculados en orden.
     */
    public static int[] generarPrimos (int max)
    {
        if (max < 2) {// max < 2
            return new int[0];
            // Vector vacío
        }

        // Declaraciones
        int TamañoDelArray = max + 1; 
        boolean[] esPrimo = new boolean[TamañoDelArray];
        int cuentaPrimos = 0;
        cuentaPrimos = TablaDeErastotenes(TamañoDelArray, esPrimo);

        int[] primos = SacarPrimosErastotenienses(TamañoDelArray, esPrimo, cuentaPrimos);

        return primos;
            
    }

    /**
     * Método que almacena en un array los números primos de un array "erastoteniense" (solo son true las posiciones primas)
     * @param esPrimo Array booleana. Las celdas primas alamacenarán "trues"; el resto "falses" 
     * @param cuentaPrimos La cantidad de primos(trues) en el array "erastoteniense" dado
     * @return Un array con todos los números primos marcadas en el array proporcionado
     */
    private static int[] SacarPrimosErastotenienses(boolean[] esPrimo, int cuentaPrimos) {
        int i;
        int j;
        // Rellenar el vector de números primos
        int[] primos = new int[cuentaPrimos];
        for (i=0, j=0; i<esPrimo.length; i++) {
            if (esPrimo[i])
                primos[j++] = i;
        }
        return primos;
    }

    /**
     * Método que dado un array de booleanos (todos en true), modifica ese array para que sólo sean true las posiciones de números primos.
     * @param esPrimo Array a modificar. Todas sus celdas deben ser true
     * @return Devuelve el número de primos en el array
     */
    private static int TablaDeErastotenes(boolean[] esPrimo) {
        int i;
        int j;
        int cuentaPrimos = 0;
        
        // Inicializar el array
        for (i=0; i<dim; i++)
            esPrimo[i] = true;
        // Eliminar el 0 y el 1, que no son primos
        esPrimo[0] = esPrimo[1] = false;
        // Criba
        for (i=2; i<Math.sqrt(esPrimo.length)+1; i++) {
            if (esPrimo[i]) {
                cuentaPrimos++;
                // Eliminar los múltiplos de i
                for (j=2*i; j<dim; j+=i)
                    esPrimo[j] = false;
            }
        }

        return cuentaPrimos;
    }
}
