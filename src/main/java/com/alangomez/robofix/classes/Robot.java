package com.alangomez.robofix.classes;

import com.alangomez.robofix.memory.Configuracion;
import java.util.Objects;

public class Robot {

    public static final int ENERGIA_MINIMA = 0;
    public static final int ENERGIA_MAXIMA = 100;

    private final String codigo;
    private final String nombre;
    private int energia;
    private ModoOperacion modo;

    public Robot(String codigo, String nombre, int energia, ModoOperacion modo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código del robot es obligatorio.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del robot es obligatorio.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        cambiarConfiguracion(energia, modo);
    }

    public static boolean esEnergiaValida(int energia) {
        return energia >= ENERGIA_MINIMA && energia <= ENERGIA_MAXIMA;
    }

    // Valida todo primero y luego asigna: nunca queda a medias
    public final void cambiarConfiguracion(int nuevaEnergia, ModoOperacion nuevoModo) {
        if (!esEnergiaValida(nuevaEnergia)) {
            throw new IllegalArgumentException("La energía debe estar entre "
                    + ENERGIA_MINIMA + " y " + ENERGIA_MAXIMA + ". Recibido: " + nuevaEnergia);
        }
        Objects.requireNonNull(nuevoModo, "El modo no puede ser nulo.");

        this.energia = nuevaEnergia;
        this.modo = nuevoModo;
    }

    public boolean tieneEnergiaSuficiente(int cantidad) {
        return cantidad >= 0 && energia >= cantidad;
    }

    public void consumirEnergia(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("No se puede consumir una cantidad negativa de energía.");
        }
        if (!tieneEnergiaSuficiente(cantidad)) {
            throw new IllegalStateException("Energía insuficiente: tiene " + energia
                    + " y se necesitan " + cantidad + ".");
        }
        energia -= cantidad;
    }

    // Memento
    public Configuracion guardarConfiguracion() {
        return new Configuracion(energia, modo);
    }

    public void restaurarConfiguracion(Configuracion configuracion) {
        Objects.requireNonNull(configuracion, "No hay configuración para restaurar.");
        cambiarConfiguracion(configuracion.getEnergia(), configuracion.getModo());
    }

    public void mostrar() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Energia: " + energia);
        System.out.println("Modo: " + modo);
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getEnergia() { return energia; }
    public ModoOperacion getModo() { return modo; }
}