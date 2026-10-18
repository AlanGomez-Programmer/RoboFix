package com.alangomez.robofix;

import com.alangomez.robofix.classes.*;
import com.alangomez.robofix.memory.HistorialConfiguracion;
import com.alangomez.robofix.util.Menu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        Robot robot = new Robot("RX-01", "Explorador", 80, ModoOperacion.NORMAL);
    
        Taller taller1 = new Taller();
        Taller taller2 = new Taller();
        
        HistorialConfiguracion historial = new HistorialConfiguracion();
        
        int opcion = 0;
        
        while (opcion != 7){
            
            Menu.mostrarMenu();
            
            opcion = leerEntero(teclado);
            
            switch (opcion) {
                case 1:
                    robot.mostrar();
                    break;
                case 2:
                    taller1.diagnosticar(robot);
                    break;
                case 3:
                    taller1.reparar(robot);
                    break;
                case 4:
                    System.out.print("Nueva energia (0-100): ");
                    int energia = leerEntero(teclado);
                    
                    System.out.print("Nuevo modo (NORMAL, AHORRO, MANTENIMIENTO): ");
                    String textoModo = teclado.nextLine();
                    
                    try {
                        ModoOperacion modo = ModoOperacion.desdeTexto(textoModo);
                        robot.cambiarConfiguracion(energia, modo);
                        System.out.println("Configuración actualizada...");
                    } catch (IllegalArgumentException e) {
                        System.out.println("No se cambió nada. " + e.getMessage());
                    }
                    break;
                case 5:
                    taller1.mostrarInventario();
                    break;
                case 6:
                    System.out.println("======================");
                    System.out.println("Inventario taller 1:");
                    taller1.mostrarInventario();
                    
                    System.out.println("======================");
                    System.out.println("Inventario taller 2:");
                    taller2.mostrarInventario();
                    
                    System.out.println("¿Comparten inventario? " 
                            + (taller1.comparteInventarioCon(taller2) ? "Sí" : "No"));
                    break;
                case 7:
                    System.out.println("Cerrando RoboFix...");
                    break;
                case 8:
                    historial.guardar(robot.guardarConfiguracion());
                    System.out.println("Configuración guardada: " + historial.obtener());
                    break;
                case 9:
                    if (historial.hayConfiguracionGuardada()) {
                        robot.restaurarConfiguracion(historial.obtener());
                        System.out.println("Configuración restaurada: " + historial.obtener());
                    } else {
                        System.out.println("No hay una configuración guardada. Usa primero la opción 8.");
                    }
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        }
        
        teclado.close();
    }
    
    private static int leerEntero(Scanner teclado) {
        while (true) {
            String linea = teclado.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.print("Escribe un número válido: ");
            }
        }
    }
}