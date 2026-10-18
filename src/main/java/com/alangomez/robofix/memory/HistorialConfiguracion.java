package com.alangomez.robofix.memory;

import java.util.Objects;

public class HistorialConfiguracion {

    private Configuracion guardada;

    public void guardar(Configuracion configuracion) {
        this.guardada = Objects.requireNonNull(configuracion, "No se puede guardar una configuración nula.");
    }

    public boolean hayConfiguracionGuardada() {
        return guardada != null;
    }

    public Configuracion obtener() {
        if (!hayConfiguracionGuardada()) {
            throw new IllegalStateException("No existe una configuración guardada.");
        }
        return guardada;
    }
}