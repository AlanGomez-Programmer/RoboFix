package com.alangomez.robofix.classes;

public class EscanerAntiguo {
    
    public String ejecutarEscaneo(String codigoRobot){
        if (codigoRobot.equals("RX-01")){
            return "RX-01|BATERIA|CRITICO|15";
        }
        
        return codigoRobot + "|SISTEMA|NORMAL|0";
    }
}