package com.alangomez.robofix.classes;

import com.alangomez.robofix.classes.adapters.adaptadorEscanerAntiguo;

public class Taller {
    // Centralización de inventario 
    private Inventario inventario = Inventario.getInstancia();
    // Adaptación de, escaner antiguo al nuevo
    private adaptadorEscanerAntiguo escaner = new adaptadorEscanerAntiguo(new EscanerAntiguo());
    
    public void diagnosticar(Robot robot){
        String resultado = escaner.ejecutarEscaneo(robot);
        
        String[] datos = resultado.split("\\|");
        
        System.out.println("\n--- DIAGNOSTICO ---");
        System.out.println("Robot: " + datos[0]);
        System.out.println("Componente: " + datos[1]);
        System.out.println("Estado:  " + datos[2]);
        System.out.println("Consumo reportado: " + datos[3]);
    }
    
    public void reparar(Robot robot){
        boolean tieneEnergia = robot.getEnergia() > 0;
        
        if (!tieneEnergia){
            robot.setEnergia(0);
            System.out.println("Lo sentimos, ya no tienes energía");
        } else {
            String resultado = escaner.ejecutarEscaneo(robot);
        
            String[] datos = resultado.split("\\|");
            String repuesto = datos[1];
            inventario.descontar(repuesto, 1);
            
            robot.setEnergia(robot.getEnergia() - 20);
            System.out.println("Robot reparado correctamente");
        }
        
        
    }
    
    public void mostrarInvetario(){
        inventario.mostrar();
    }
}
