package paquete;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class AlgoritmoKMeansTest {
    @Test
    void estimate() {
        AlgoritmoKMeans main = new AlgoritmoKMeans(3, 5, 50);
        CSV tabla = new CSV();
        main.train(tabla.readTableWithLabels("iris.csv"));
        List<Double> data = new ArrayList<>();
        data.add(1.0); data.add(1.7); data.add(1.1); data.add(1.0);
        assertEquals("Pertenece al grupo: 2", main.estimate(new Row(data)));

        main=new AlgoritmoKMeans(2,3,50);
        main.train(tabla.readTableWithLabels("iris.csv"));
        data=new ArrayList<>();
        data.add(7.0); data.add(7.7); data.add(7.1); data.add(5.0);
        assertEquals("Pertenece al grupo: 1", main.estimate(new Row(data)));

        main=new AlgoritmoKMeans(1,3,50);
        main.train(tabla.readTableWithLabels("iris.csv"));
        data=new ArrayList<>();
        data.add(3.0); data.add(1.7); data.add(4.1); data.add(9.0);
        assertEquals("Pertenece al grupo: 0", main.estimate(new Row(data)));


        main=new AlgoritmoKMeans(4,3,50);
        main.train(tabla.readTableWithLabels("iris.csv"));
        data=new ArrayList<>();
        data.add(1.0); data.add(1.3); data.add(1.0); data.add(2.0);
        assertEquals("Pertenece al grupo: 2", main.estimate(new Row(data)));
    }



}