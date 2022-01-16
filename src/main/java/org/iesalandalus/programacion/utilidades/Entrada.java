package org.iesalandalus.programacion.utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Clase que nos permite leer datos por teclado asegurándose
 * que el tipo de dato introducido es compatible con el esperado.
 * 
 * @author pepino
 */
public class Entrada {
	
	/**
	 * Constructor privado para evitar que java genere el suyo
	 * por defecto y así evitar que se puedan instanciar objetos
	 */
	private Entrada() {
		// No se permiten instanciar objetos de esta clase
	}
	
	/**
	 * Método estático que lee una cadena por teclado
	 * @return la cadena leída
	 */
	public static String cadena() {
            String valor = "";
            InputStreamReader flujo = new InputStreamReader(System.in);
            BufferedReader teclado = new BufferedReader(flujo);
            try {
                    valor = teclado.readLine();
            } catch (IOException e) {
                    System.out.print("Error de Entrada/Salida. Inténtalo de nuevo: ");
            }
            return valor;
	}

	/**
	 * Método estático que lee un entero por teclado y se asegura
	 * que el valor introducido es compatible con un entero
	 * @return el entero leído
	 */
	public static int entero() {
            int valor = 0;
            boolean leido = false;
            do {
                    try {
                            valor = Integer.parseInt(cadena());
                            leido = true;
                    } catch (NumberFormatException e) {
                            System.out.print("ERROR: Esperaba un entero. Inténtalo de nuevo: ");
                    }
            } while (!leido);
            return valor;
	}
	
	/**
	 * Método estático que lee un entero largo (long) por teclado y se asegura
	 * que el valor introducido es compatible con un entero largo
	 * @return el entero largo leído
	 */
	public static long enteroLargo() {
            long valor = 0;
            boolean leido = false;
            do {
                    try {
                            valor = Long.parseLong(cadena());
                            leido = true;
                    } catch (NumberFormatException e) {
                            System.out.print("ERROR: Esperaba un entero largo. Inténtalo de nuevo: ");
                    }
            } while (!leido);
            return valor;
	}
	
	/**
	 * Método estático que lee un real (float) por teclado y se asegura
	 * que el valor introducido es compatible con un real
	 * @return el real leído
	 */
	public static float real() {
            float valor = 0;
            boolean leido = false;
            do {
                    try {
                            valor = Float.parseFloat(cadena());
                            leido = true;
                    } catch (NumberFormatException e) {
                            System.out.print("ERROR: Esperaba un real. Inténtalo de nuevo: ");
                    }
            } while (!leido);
            return valor;
	}
	
	/**
	 * Método estático que lee un real de doble precisión (double) 
	 * por teclado y se asegura que el valor introducido es compatible 
	 * con un real de doble precisión
	 * @return el real de doble precisión leído
	 */
	public static double realDoble() {
            double valor = 0;
            boolean leido = false;
            do {
                    try {
                            valor = Double.parseDouble(cadena());
                            leido = true;
                    } catch (NumberFormatException e) {
                            System.out.print("ERROR: Esperaba un real de doble precisión. Inténtalo de nuevo: ");
                    }
            } while (!leido);
            return valor;
	}
	
	/**
	 * Método estático que lee un caracter por teclado y se asegura
	 * que el valor introducido es compatible con un caracter
	 * @return el caracter leído
	 */
	public static char caracter() {
            String valor = "";
            boolean leido = false;
            do {
                    valor = cadena();
                    if (valor.length() == 1)
                            leido = true;
                    else 
                            System.out.print("ERROR: Esperaba un caracter. Inténtalo de nuevo: ");
            } while (!leido);
            return valor.charAt(0);
	}

}
