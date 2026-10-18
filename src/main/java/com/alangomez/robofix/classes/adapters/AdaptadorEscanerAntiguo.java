package com.alangomez.robofix.classes.adapters;

import com.alangomez.robofix.classes.Diagnostico;
import com.alangomez.robofix.classes.EscanerAntiguo;
import com.alangomez.robofix.classes.Robot;
import com.alangomez.robofix.interfaces.EscanerExterno;
import java.util.Objects;

public class AdaptadorEscanerAntiguo implements EscanerExterno {

    private static final String SEPARADOR = "\\|";
    private static final int CAMPOS_ESPERADOS = 4;

    private final EscanerAntiguo escanerAntiguo;

    public AdaptadorEscanerAntiguo(EscanerAntiguo escanerAntiguo) {
        this.escanerAntiguo = Objects.requireNonNull(escanerAntiguo,
                "El sistema antiguo no puede ser nulo");
    }

    @Override
    public Diagnostico escanear(Robot robot) {
        Objects.requireNonNull(robot, "No se puede escanear un robot nulo.");

        String respuesta = escanerAntiguo.ejecutarEscaneo(robot.getCodigo());

        if (respuesta == null || respuesta.isBlank()) {
            throw new IllegalStateException("El escáner no devolvió resultado.");
        }

        String[] datos = respuesta.split(SEPARADOR);
        if (datos.length != CAMPOS_ESPERADOS) {
            throw new IllegalStateException("Formato de diagnóstico inválido: " + respuesta);
        }

        String codigo = datos[0].trim();
        String componente = datos[1].trim();
        String estado = datos[2].trim();

        if (!codigo.equals(robot.getCodigo())) {
            throw new IllegalStateException("El diagnóstico corresponde a otro robot: " + codigo);
        }
        if (componente.isEmpty() || estado.isEmpty()) {
            throw new IllegalStateException("El diagnóstico tiene campos vacíos: " + respuesta);
        }

        int consumo;
        try {
            consumo = Integer.parseInt(datos[3].trim());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Consumo no numérico en el diagnóstico: " + datos[3]);
        }

        return new Diagnostico(codigo, componente, estado, consumo);
    }
}