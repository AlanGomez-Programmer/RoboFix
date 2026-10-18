package com.alangomez.robofix.classes;

import com.alangomez.robofix.classes.adapters.AdaptadorEscanerAntiguo;
import com.alangomez.robofix.interfaces.EscanerExterno;
import java.util.Objects;

public class Taller {

    public static final int COSTO_ENERGIA_REPARACION = 20;
    public static final int REPUESTOS_POR_REPARACION = 1;

    private final Inventario inventario = Inventario.getInstancia();
    private final EscanerExterno escaner;

    public Taller() {
        this(new AdaptadorEscanerAntiguo(new EscanerAntiguo()));
    }

    public Taller(EscanerExterno escaner) {
        this.escaner = Objects.requireNonNull(escaner, "El taller necesita un escáner.");
    }

    public void diagnosticar(Robot robot) {
        try {
            Diagnostico diagnostico = escaner.escanear(robot);
            System.out.println("\n--- DIAGNOSTICO ---");
            System.out.println("Robot: " + diagnostico.getCodigoRobot());
            System.out.println("Componente: " + diagnostico.getComponente());
            System.out.println("Estado: " + diagnostico.getEstado());
            System.out.println("Consumo reportado: " + diagnostico.getConsumo());
        } catch (IllegalStateException e) {
            System.out.println("No se pudo diagnosticar: " + e.getMessage());
        }
    }

    public boolean reparar(Robot robot) {
        Diagnostico diagnostico;
        try {
            diagnostico = escaner.escanear(robot);
        } catch (IllegalStateException e) {
            System.out.println("Reparación cancelada. Diagnóstico inválido: " + e.getMessage());
            return false;
        }

        if (!diagnostico.requiereReparacion()) {
            System.out.println("El robot no necesita reparación.");
            return false;
        }

        String repuesto = diagnostico.getComponente();

        if (!inventario.existeRepuesto(repuesto)) {
            System.out.println("Reparación fallida: el taller no maneja el repuesto " + repuesto + ".");
            return false;
        }
        if (!inventario.hayStock(repuesto, REPUESTOS_POR_REPARACION)) {
            System.out.println("Reparación fallida: no hay stock de " + repuesto + ".");
            return false;
        }
        if (!robot.tieneEnergiaSuficiente(COSTO_ENERGIA_REPARACION)) {
            System.out.println("Reparación fallida: el robot necesita al menos "
                    + COSTO_ENERGIA_REPARACION + " de energía y tiene " + robot.getEnergia() + ".");
            return false;
        }

        inventario.descontar(repuesto, REPUESTOS_POR_REPARACION);
        robot.consumirEnergia(COSTO_ENERGIA_REPARACION);
        System.out.println("Robot reparado correctamente (se usó 1 " + repuesto + ").");
        return true;
    }

    public void mostrarInventario() {
        inventario.mostrar();
    }

    public boolean comparteInventarioCon(Taller otro) {
        return otro != null && this.inventario == otro.inventario;
    }
}