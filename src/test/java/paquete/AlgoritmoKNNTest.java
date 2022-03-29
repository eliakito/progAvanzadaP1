package paquete;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import paquete.practica1.AlgoritmoKNN;
import paquete.practica1.CSV;
import paquete.practica1.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgoritmoKNNTest {

    @Test
    @DisplayName("Test Estimacion Algoritmo KNN")
    void estimate() {
        AlgoritmoKNN knn = new AlgoritmoKNN();
        CSV main = new CSV();
        TableWithLabels tableWithLabels = main.readTableWithLabels("iris.csv");
        knn.train(tableWithLabels);
        // TEST SETOSAS
        List<Double> sample = new ArrayList<Double>();
        sample.add(5.1); sample.add(3.5); sample.add(1.4); sample.add(0.2);
        assertEquals("Iris-setosa", knn.estimate(sample));
        sample = new ArrayList<Double>();
        sample.add(4.9); sample.add(3.1); sample.add(1.5); sample.add(0.1);
        assertEquals("Iris-setosa", knn.estimate(sample));

        // TEST VERSICOLORES
        sample = new ArrayList<Double>();
        sample.add(6.4); sample.add(3.2); sample.add(4.5); sample.add(1.5);
        assertEquals("Iris-versicolor", knn.estimate(sample));
        sample = new ArrayList<Double>();
        sample.add(5.5); sample.add(2.4); sample.add(3.8); sample.add(1.1);
        assertEquals("Iris-versicolor", knn.estimate(sample));

        // TEST VIRGINICAS
        sample = new ArrayList<Double>();
        sample.add(6.3); sample.add(2.9); sample.add(5.6); sample.add(1.8);
        assertEquals("Iris-virginica", knn.estimate(sample));
        sample = new ArrayList<Double>();
        sample.add(6.2); sample.add(2.7); sample.add(5.9); sample.add(2.0);
        assertEquals("Iris-virginica", knn.estimate(sample));
    }
}