package paquete;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import paquete.practica1.CSV;
import paquete.practica1.RegresionLineal;
import paquete.practica1.Table;

import static org.junit.jupiter.api.Assertions.*;

class RegresionLinealTest {

    @Test
    @DisplayName("Test Estimación Valores")
    void estimate() {
        RegresionLineal reg = new RegresionLineal();
        CSV main = new CSV();
        Table tabla = main.readTable("miles_dollars.csv");
        try {
            reg.train(tabla);
        } catch (ArithmeticException e) {
            System.out.println("División por cero --> "+ e.getMessage());
            e.printStackTrace();
        }
        // LOS VALORES ESPERADOS SE OBTIENEN MEDIANTE LA SIGUIENTE FORMULA
        // ALPHA*SAMPLE + BETA
        // SIENDO ALPHA = 1.255 Y BETA = 274.85
        assertEquals(2575.265, reg.estimate(1833.0));
        assertEquals(3102.365, reg.estimate(2253.0));
        assertEquals(5336.265, reg.estimate(4033.0));
    }

    @Test
    @DisplayName("Test Valor Alpha")
    void getAlpha() {
        RegresionLineal reg = new RegresionLineal();
        // Para ejecutar el train y actualizar el valor de Alpha
        reg.estimate(0.0);
        assertEquals(1.255, reg.getAlpha());
    }

    @Test
    @DisplayName("Test Valor Beta")
    void getBeta() {
        RegresionLineal reg = new RegresionLineal();
        // Para ejecutar el train y actualizar el valor de Beta
        reg.estimate(0.0);
        assertEquals(274.85, reg.getBeta());
    }
}