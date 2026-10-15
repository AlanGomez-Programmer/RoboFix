package com.alangomez.robofix.classes;

public class Robot {
    private String codigo;
    private String nombre;
    private int energia;
    private String modo;

    public Robot(String codigo, String nombre, int energia, String modo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.energia = energia;
        this.modo = modo;
    }
    
    public void mostrar(){
            System.out.println("Codigo: " + codigo);
            System.out.println("Nombre: " + nombre);
            System.out.println("Energia: " + energia);
            System.out.println("Modo: " + modo);
    }
    
    public String getCodigo(){
        return codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getEnergia(){
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
    
    
}
