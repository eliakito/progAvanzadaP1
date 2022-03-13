package paquete;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgoritmoKMeansTest {

    @Test
    void train() {
        AlgoritmoKMeans main = new AlgoritmoKMeans(3, 0, 0);
        CSV tabla = new CSV();
        main.train(tabla.readTable("iris.csv"));
        for(int i = 0; i < main.listaRepresentantes.size(); i++)
            System.out.println(main.listaRepresentantes.get(i));
    }

    @Test
    void estimate() {
    }
}