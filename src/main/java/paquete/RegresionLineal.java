package paquete;

import java.util.ArrayList;
import java.util.List;

public class RegresionLineal implements Algorithm <Table, Double, Double> {
    private Double alpha = 0.0;
    private Double beta = 0.0;

    public void train(Table data) throws ArithmeticException {
        List<Double> columna1 = data.getColumnAt(0);
        List<Double> columna2 = data.getColumnAt(1);

        Double mediaY = 0.0, mediaX = 0.0;
        for(int i = 0; i < columna2.size(); i++) {
            mediaY += columna2.get(i);
            mediaX += columna1.get(i);
        }

        mediaX = mediaX / columna1.size();
        mediaY = mediaY / columna2.size();

        Double sumatorio1 = 0.0, sumatorio2 = 0.0;
        for(int i = 0; i < columna1.size(); i++) {
            Double difX = (columna1.get(i)-mediaX);
            sumatorio1 += difX*(columna2.get(i)-mediaY);
            sumatorio2 += difX*difX;
        }
        alpha = sumatorio1/sumatorio2;
        beta = mediaY-alpha*mediaX;
        // Redondear los valores de alpha y beta a 3 decimales

        alpha = Math.round(alpha*1000.0)/1000.0;
        beta = Math.round(beta*1000.0)/1000.0;
    }

    public Double estimate(Double sample) {
        return (alpha*sample)+beta;
    }

    public Double getAlpha() {
        return alpha;
    }

    public Double getBeta() {
        return beta;
    }
}
