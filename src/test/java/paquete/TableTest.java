package paquete;

import org.junit.jupiter.api.DisplayName;
import paquete.practica1.Table;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @org.junit.jupiter.api.Test
    @DisplayName("Test getRowAt()")
    void getRowAt() {
        Table tabla = new Table();
        // TEST FILA 0
        List<Double> lista = new ArrayList<Double>();
        lista.add(1211.0); lista.add(1802.0);
        assertArrayEquals(lista.toArray(), tabla.getRowAt(0).getData().toArray());

        // TEST FILA 15
        lista = new ArrayList<Double>();
        lista.add(3643.0); lista.add(5298.0);
        assertArrayEquals(lista.toArray(), tabla.getRowAt(15).getData().toArray());

        // TEST ULTIMA FILA
        lista = new ArrayList<Double>();
        lista.add(5439.0); lista.add(6964.0);
        assertArrayEquals(lista.toArray(), tabla.getRowAt(24).getData().toArray());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Test getColumnAt()")
    void getColumnAt() {
        Table tabla = new Table();
        // TEST COLUMNA 0
        List<Double> lista = new ArrayList<Double>();
        lista.add(1211.0); lista.add(1345.0); lista.add(1422.0); lista.add(1687.0);
        lista.add(1849.0); lista.add(2026.0); lista.add(2133.0); lista.add(2253.0);
        lista.add(2400.0); lista.add(2468.0); lista.add(2699.0); lista.add(2806.0);
        lista.add(3082.0); lista.add(3209.0); lista.add(3466.0); lista.add(3643.0);
        lista.add(3852.0); lista.add(4033.0); lista.add(4267.0); lista.add(4498.0);
        lista.add(4533.0); lista.add(4804.0); lista.add(5090.0); lista.add(5233.0); lista.add(5439.0);
        assertArrayEquals(lista.toArray(), tabla.getColumnAt(0).toArray());

        // TEST COLUMNA 1
        lista = new ArrayList<Double>();
        lista.add(1802.0); lista.add(2405.0); lista.add(2005.0); lista.add(2511.0);
        lista.add(2332.0); lista.add(2305.0); lista.add(3016.0); lista.add(3385.0);
        lista.add(3090.0); lista.add(3694.0); lista.add(3371.0); lista.add(3998.0);
        lista.add(3555.0); lista.add(4692.0); lista.add(4244.0); lista.add(5298.0);
        lista.add(4801.0); lista.add(5147.0); lista.add(5738.0); lista.add(6420.0);
        lista.add(6059.0); lista.add(6426.0); lista.add(6321.0); lista.add(7026.0); lista.add(6964.0);
        assertArrayEquals(lista.toArray(), tabla.getColumnAt(1).toArray());
    }
}