package com.alangomez.robofix.classes;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    
    private Map<String, Integer> repuestos = new HashMap<>();
    
    public Inventario(){
        repuestos.put("BATERIA", 2);
        repuestos.put("MOTOR", 3);
        repuestos.put("SENSOR", 4);
    }
    
    public void mostrar(){
        for (String repuesto : repuestos.keySet()){
            System.out.println(
                    repuesto + ": " + repuestos.get(repuesto)
            );
        }
    }
    
    public void descontar(String repuesto, int cantidad){
        int actual = repuestos.get(repuesto);
        repuestos.put(repuesto, actual - cantidad);
    }
    
    public int consultar(String repuesto){
        return repuestos.get(repuesto);
    }
}
