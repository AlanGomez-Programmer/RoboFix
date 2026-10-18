package com.alangomez.robofix.classes;

public final class Diagnostico {

    public static final String ESTADO_NORMAL = "NORMAL";

    private final String codigoRobot;
    private final String componente;
    private final String estado;
    private final int consumo;

    public Diagnostico(String codigoRobot, String componente, String estado, int consumo) {
        this.codigoRobot = codigoRobot;
        this.componente = componente;
        this.estado = estado;
        this.consumo = consumo;
    }

    public boolean requiereReparacion() {
        return !ESTADO_NORMAL.equals(estado);
    }

    public String getCodigoRobot() { return codigoRobot; }
    public String getComponente() { return componente; }
    public String getEstado() { return estado; }
    public int getConsumo() { return consumo; }
}