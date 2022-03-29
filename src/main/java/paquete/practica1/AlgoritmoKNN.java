package paquete.practica1;

import paquete.practica1.TableWithLabels;
import paquete.practica2.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoKNN implements Algorithm<TableWithLabels, String, List<Double>> {
    private TableWithLabels tabla;

    public AlgoritmoKNN() {
        tabla = new TableWithLabels();
    }

    public void train(TableWithLabels data) {
        tabla = data;
    }

    public String estimate(List<Double> sample) {
        double minDistancia = Double.POSITIVE_INFINITY;
        String label = null;
        for(int i = 0; i < tabla.numeroFilas(); i++) {
            double distancia = distanciaEuclidea(sample, tabla.getRowAt(i).getData());
            if(distancia <= minDistancia) {
                minDistancia = distancia;
                label = tabla.getRowAt(i).getLabel();
            }
        }
        return label;
    }

    private Double distanciaEuclidea(List<Double> sample, List<Double> representante) {
        double res = 0.0;
        for(int i = 0; i < representante.size(); i++)
            res += Math.pow((sample.get(i)-representante.get(i)),2);
        return Math.sqrt(res);
    }
}