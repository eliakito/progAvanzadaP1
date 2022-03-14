package paquete;

import org.junit.jupiter.api.Test;

class AlgoritmoKMeansTest {

    @Test
    void train() {
        AlgoritmoKMeans main = new AlgoritmoKMeans(3, 0, 0);
        CSV tabla = new CSV();
        main.train(tabla.readTable("iris.csv"));
        for(int i = 0; i < main.listaRepresentantesGrupo.size(); i++)
            System.out.println(main.listaRepresentantesGrupo.get(i));
    }

    @Test
    void estimate() {
    }
}