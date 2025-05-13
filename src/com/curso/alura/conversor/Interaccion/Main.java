package com.curso.alura.conversor.Interaccion;
import com.curso.alura.conversor.toGson.ExchangeRateResponse;
import com.curso.alura.conversor.callapi.Solicitud;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String base, target;
        int amount;

        System.out.println("Conversor de divisas"); // Corregido "Coversor" a "Conversor"
        System.out.println("====================\n");

        Solicitud solicitud = new Solicitud("D:\\Proyectos-Java\\conversor\\src\\key");

        ListaDivisas listaDivisas = new ListaDivisas();
        listaDivisas.mostrarDivisasDisponibles();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese la divisa base: ");
            base = sc.next().toUpperCase(); // Convertir a mayúsculas para consistencia
            System.out.println("Ingrese la divisa a convertir: ");
            target = sc.next().toUpperCase(); // Convertir a mayúsculas para consistencia

            if (listaDivisas.esDivisaDisponible(base) && listaDivisas.esDivisaDisponible(target)) {
                System.out.println("Ingrese la cantidad a convertir: ");
                amount = sc.nextInt();

                try {
                    // Guardar la respuesta de la API
                    ExchangeRateResponse respuesta = solicitud.obtenerCotizacion(base, target, amount);

                    // Mostrar información completa de la cotización
                    System.out.println("\nCotización:");
                    System.out.println("Base: " + respuesta.getBase_code());
                    System.out.println("Destino: " + respuesta.getTarget_code());
                    System.out.println("Cantidad: " + respuesta.getAmount());
                    System.out.println("Resultado: " + respuesta.getConversion_result());
                    System.out.println("Tasa de cambio: " + respuesta.getConversion_rate());
                    System.out.println("Última actualización: " + respuesta.getTime_last_update_utc());
                } catch (Exception e) {
                    System.out.println("Error al obtener la cotización: " + e.getMessage());
                }
            } else {
                System.out.println("Una o ambas divisas ingresadas no están disponibles");
            }
        }
    }
}
