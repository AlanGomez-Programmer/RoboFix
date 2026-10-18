package com.alangomez.robofix.interfaces;

import com.alangomez.robofix.classes.Diagnostico;
import com.alangomez.robofix.classes.Robot;

public interface EscanerExterno {

    Diagnostico escanear(Robot robot);
}