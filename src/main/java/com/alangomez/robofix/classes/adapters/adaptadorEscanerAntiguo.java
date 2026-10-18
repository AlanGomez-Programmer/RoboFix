package com.alangomez.robofix.classes.adapters;

import com.alangomez.robofix.classes.*;
import com.alangomez.robofix.interfaces.EscanerExterno;
import java.util.Objects;

public class adaptadorEscanerAntiguo implements EscanerExterno {
    
    private EscanerAntiguo escanerAntiguo;
    
    public adaptadorEscanerAntiguo(EscanerAntiguo escanerAntiguo){
        this.escanerAntiguo = Objects.requireNonNull(escanerAntiguo, 
                "El sistema antiguo no puede ser nulo");
    }

    @Override
    public String ejecutarEscaneo(Robot robot) {
        if (robot.getCodigo().contains("RX-01")){
            return robot.getNombre() + "|BATERIA|CRITICO|15";
        }
        
        return robot.getCodigo() + "|SISTEMA|NORMAL|0";
    }    
    
}
