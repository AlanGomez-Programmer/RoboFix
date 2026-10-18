package com.alangomez.robofix;

import com.alangomez.robofix.classes.*;
import com.alangomez.robofix.util.Menu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        Robot robot = new Robot("RX-01", "Explorador", 80, "NORMAL");
    
        Taller taller1 = new Taller();
        Taller taller2 = new Taller();
        
        System.out.println(taller1);
        System.out.println(taller2);
        
        
        int opcion = 0;
        
        while (opcion != 7){
            
            Menu.mostrarMenu();
            
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
                    System.out.println("======================");
                    System.out.println("Inventario taller 1:");
                    taller1.mostrarInvetario();
                    
                    System.out.println("======================");
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
