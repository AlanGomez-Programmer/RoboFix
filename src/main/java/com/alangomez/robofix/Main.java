package com.alangomez.robofix;

import com.alangomez.robofix.classes.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        Robot robot = new Robot("RX-01", "Explorador", 80, "NORMAL");
    
        Taller taller1 = new Taller();
        Taller taller2 = new Taller();
        
        int opcion = 0;
        
        while (opcion != 7){
            System.out.println("\n===== ROBOFIX =====");
            System.out.println("1. Ver robot");
            System.out.println("2. Diagnosticar robot");
            System.out.println("3. Reparar robot");
            System.out.println("4. Cambiar configuración");
            System.out.println("5. Ver inventario");
            System.out.println("6. Comparar inventario");
            System.out.println("7. Salir");
            System.out.print("Opcion: ");
            
            opcion = teclado.nextInt();
            teclado.nextLine();
            
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
                    System.out.print("Nueva energia: ");
                    int energia = teclado.nextInt();
                    teclado.nextLine();
                    
                    System.out.print("Nuevo modo: ");
                    String modo = teclado.nextLine();
                    
                    robot.setEnergia(energia);
                    robot.setModo(modo);
                    System.out.println("Configuración actualizada...");
                    break;
                case 5:
                    taller1.mostrarInvetario();
                    break;
                case 6:
                    System.out.println("Inventario taller 1:");
                    taller1.mostrarInvetario();
                    
                    System.out.println("Inventario taller 2:");
                    taller2.mostrarInvetario();
                    break;
                case 7:
                    System.out.println("Cerrando RoboFix...");
                    break;
                default:
                    System.out.println("Opción invalida");;
            }
        }
        
        teclado.close();
    }
}
