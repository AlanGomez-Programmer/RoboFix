package com.alangomez.robofix.classes;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    
    private static Inventario instancia;
    private Map<String, Integer> repuestos = new HashMap<>();
    
    private Inventario(){
        repuestos.put("BATERIA", 5);
        repuestos.put("MOTOR", 3);
        repuestos.put("SENSOR", 4);
    }
    
    public static Inventario getInstancia(){
        if (instancia == null){
            instancia = new Inventario();
        }
        
        return instancia;
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
         
        if (actual <=  0){
            repuestos.replace(repuesto, 0);
            System.out.println("El respuesto: " + repuesto + " ya no tiene suficiente stock");
        } else {
            repuestos.put(repuesto, actual - cantidad);
        }
        
    }
    
    public int consultar(String repuesto){
        return repuestos.get(repuesto);
    }
}
