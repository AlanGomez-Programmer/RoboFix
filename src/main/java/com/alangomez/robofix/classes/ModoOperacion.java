package com.alangomez.robofix.classes;

import java.util.Arrays;

public enum ModoOperacion {
    NORMAL,
    AHORRO,
    MANTENIMIENTO;

    public static ModoOperacion desdeTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El modo no puede estar vacío.");
        }
        try {
            return ModoOperacion.valueOf(texto.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Modo inválido: \"" + texto.trim() + "\". Modos permitidos: "
                    + Arrays.toString(values()));
        }
    }
}