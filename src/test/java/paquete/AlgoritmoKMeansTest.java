package paquete;

import org.junit.jupiter.api.Test;

import java.util.Random;

class AlgoritmoKMeansTest {

    @Test
    void train()  {

        AlgoritmoKMeans main = new AlgoritmoKMeans(3, 5,55);
        CSV tabla = new CSV();
        main.train(tabla.readTableWithLabels("iris.csv"));
        for(int i = 0; i < main.listaRepresentantesGrupo.size(); i++)
            System.out.println(main.listaRepresentantesGrupo.get(i).getData().toString());
    }

    @Test
    void estimate() {
    }
}