package com.alangomez.robofix.memory;

import com.alangomez.robofix.classes.*;
import java.util.Objects;

public final class Configuracion {

    private final int energia;
    private final ModoOperacion modo;

    public Configuracion(int energia, ModoOperacion modo) {
        if (!Robot.esEnergiaValida(energia)) {
            throw new IllegalArgumentException("No se puede guardar una configuración con energía inválida: " + energia);
        }
        this.energia = energia;
        this.modo = Objects.requireNonNull(modo, "No se puede guardar una configuración sin modo.");
    }

    public int getEnergia() { return energia; }
    public ModoOperacion getModo() { return modo; }

    @Override
    public String toString() {
        return "energía=" + energia + ", modo=" + modo;
    }
}