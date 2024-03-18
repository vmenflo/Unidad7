/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 *
 * @author victor
 *
 * Tenemor que crear un método que vaya añadiendo a un fichero lo que escribas
 * hasta que pongas FIN
 */
public class EjercicioDeClase {

    public static void main(String[] args) {
        boolean salir = false;
        Scanner teclado = new Scanner(System.in);
        String menu = """
               ¿Qué deseas hacer?
                    1-Escribir
                    2-Leer
                    3-Salir
                            """;
        do {
            System.out.println(menu);
            int opcion = teclado.nextInt();
            teclado.nextLine();//Limpiar

            switch (opcion) {
                case 1 -> {
                    System.out.println("Opción escribir");
                    System.out.println("¿En que fichero quieres escribir?");
                    String nombreFichero = teclado.nextLine();
                    escribirString(nombreFichero);
                }
                case 2 -> {
                    System.out.println("Opción leer");
                    System.out.println("¿Qué fichero quieres leer?");
                    String idFichero = teclado.nextLine();
                    leerFichero(idFichero);
                }
                case 3 -> {
                    System.out.println("Saliendo...");
                    salir = true;
                }

            }

        } while (!salir);

    }

    // Escribe un String a un fichero, sobreescribiendo si existe
    public static void escribirString(String nombreFichero) {
        Scanner teclado = new Scanner(System.in);
        String entrada = " ";

        do {
            System.out.println("¿Qué deseas escribir?");
            entrada = teclado.nextLine();
            if (!entrada.equalsIgnoreCase("fin")) {
                try {
                    Files.write(Paths.get(nombreFichero), (entrada + "\n").getBytes(StandardCharsets.UTF_8),
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException ex) {
                    System.out.println("Error creando el fichero");
                }
            }
        } while (!entrada.equalsIgnoreCase("fin"));
    }

    //Leer fichero
    public static void leerFichero(String idFichero) {

        // Variables para guardar los datos que se van leyendo
        String[] tokens;
        String linea;

        System.out.println("Leyendo el fichero: " + idFichero);

        // Inicialización del flujo "datosFichero" en función del archivo llamado "idFichero"
        // Estructura try-with-resources. Permite cerrar los recursos una vez finalizadas
        // las operaciones con el archivo
        try (Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {
            // hasNextLine devuelve true mientras haya líneas por leer
            while (datosFichero.hasNextLine()) {
                // Guarda la línea completa en un String
                linea = datosFichero.nextLine();
                // Se guarda en el array de String cada elemento de la
                // línea en función del carácter separador de campos del fichero CSV
                tokens = linea.split("\n");
                for (String string : tokens) {
                    System.out.print(string + "\t");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
