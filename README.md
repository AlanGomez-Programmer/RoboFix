# RoboFix


## Exploración y revisión de ejecución antes de modificar

**Hallazgos iniciales**

1. El sistema permite el registo de energía negativa al ingresarlo.
2. El sistema registra modos inexistentes al momento de agregralo.
3. El sistema tiene un error de control de energía al momente de reparla, ya que al momento de repar un robot varias veces, aunque el roboto no tenga energía se le sigue restanto, por lo tanto la energía queda en números negativos.
4. **¿Los dos talleres compartene el mismo inventario?**

    Raelmente no se comparten el invetario ya que el constructor de la instancia "Taller" tiene por default los mismos valores, pero al momento de crear un nuevo taller (objeto) se crea pero con los datos por default eso quiere decir que no comparte el mismo inventario con otro taller.

5. **¿Comó podrías recuperar la configuración anterior del robot?**

    Para recuperar la configuración anterior del robot se aplica el patrón de diseño **memento** ya que es un patrón que guarda los estados de una instancia.

6. El sistema no tiene condicionales en el control del inventario ya que al quedarse sin producto y se hace una resparación, el sistema permite restar pero eso quiere decir que esta sumando las cantidades negativas.

