package com.reto.conversor.Interaccion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ListaDivisas {
    private Set<String> divisasDisponibles = new HashSet<>(Arrays.asList("USD", "EUR", "GBP",
            "JPY", "CAD", "AUD", "CHF", "CNY", "HKD", "MXN", "INR", "BRL",
            "RUB", "ZAR", "SGD", "NZD", "TRY", "SEK", "NOK", "DKK"
    ));

    public Set<String> getDivisasDisponibles() {
        return new HashSet<>(divisasDisponibles);
    }

    public boolean esDivisaDisponible(String divisa){
        return divisasDisponibles.contains(divisa.toUpperCase());

    }

    public void mostrarDivisasDisponibles(){
        System.out.println("Divisas disponibles:");
        divisasDisponibles.forEach(divisa -> System.out.println(divisa));
    }
}
