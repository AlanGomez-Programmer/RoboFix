package com.alangomez.robofix.classes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Inventario {

    private static Inventario instancia;

    private final Map<String, Integer> repuestos = new LinkedHashMap<>();

    private Inventario() {
        repuestos.put("BATERIA", 2);
        repuestos.put("MOTOR", 3);
        repuestos.put("SENSOR", 4);
    }

    public static synchronized Inventario getInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }

    public void mostrar() {
        repuestos.forEach((repuesto, cantidad) ->
                System.out.println(repuesto + ": " + cantidad));
    }

    public boolean existeRepuesto(String repuesto) {
        return repuestos.containsKey(repuesto);
    }

    public int consultar(String repuesto) {
        return repuestos.getOrDefault(repuesto, 0);
    }

    public boolean hayStock(String repuesto, int cantidad) {
        return cantidad > 0 && consultar(repuesto) >= cantidad;
    }

    public void descontar(String repuesto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a descontar debe ser mayor que 0.");
        }
        if (!existeRepuesto(repuesto)) {
            throw new IllegalArgumentException("El repuesto " + repuesto + " no existe en el inventario.");
        }
        if (!hayStock(repuesto, cantidad)) {
            throw new IllegalStateException("Stock insuficiente de " + repuesto
                    + ": hay " + consultar(repuesto) + ", se necesitan " + cantidad + ".");
        }
        repuestos.put(repuesto, consultar(repuesto) - cantidad);
    }
}