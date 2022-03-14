package paquete;

import java.util.ArrayList;
import java.util.List;

public class AlgoritmoKNN implements Algorithm <TableWithLabels, String, List<Double>> {
    // LISTAS DE LAS CARACTERÍSTICAS DE CADA FLOR
    private List<Double> setosa = new ArrayList<Double>();
    private List<Double> versicolor = new ArrayList<Double>();
    private List<Double> virginica = new ArrayList<Double>();

    // CARACTERÍSTICAS IRIS-SETOSA
    private Double longitudSepaloSetosa = 0.0;
    private Double anchuraSepaloSetosa = 0.0;
    private Double longitudPetaloSetosa = 0.0;
    private Double anchuraPetaloSetosa = 0.0;

    // CARACTERÍSTICAS IRIS-VERSICOLOR
    private Double longitudSepaloVersicolor = 0.0;
    private Double anchuraSepaloVersicolor = 0.0;
    private Double longitudPetaloVersicolor = 0.0;
    private Double anchuraPetaloVersicolor = 0.0;

    // CARACTERÍSTICAS IRIS-VIRGINICA
    private Double longitudSepaloVirginica = 0.0;
    private Double anchuraSepaloVirginica = 0.0;
    private Double longitudPetaloVirginica = 0.0;
    private Double anchuraPetaloVirginica = 0.0;

    public void train(TableWithLabels data) {
        int contadorSetosa = 0;
        int contadorVersicolor = 0;
        int contadorVirginica = 0;

        for(int i = 0; i < data.getColumnAt(0).size(); i++) {
            RowWithLabel fila = data.getRowAt(i);
            List<Double> lista = fila.getData();
            if(fila.getLabel().equals("Iris-Setosa")) {
                System.out.println("asdfasdfasdfasdf"+data.getColumnAt(3).toString());
                calcularSetosa(lista);
                contadorSetosa++;
            }
            if(fila.getLabel().equals("Iris-versicolor")) {
                calcularVersicolor(lista);
                contadorVersicolor++;
            }
            if(fila.getLabel().equals("Iris-virginica")) {
                calcularVirginica(lista);
                contadorVirginica++;
            }
        }
        calculaMedias(contadorSetosa, contadorVersicolor, contadorVirginica);
        actualizaListas();
    }

    public String estimate(List<Double> sample) {
       //TODO
        String estimacion = null;
        Double distSetosa = algoritmoKNN(sample, setosa);
        Double distVersicolor = algoritmoKNN(sample, versicolor);
        Double distVirginica = algoritmoKNN(sample, virginica);
        if(distSetosa <= distVersicolor && distSetosa <= distVirginica) {
            estimacion = "Iris-setosa";
        } else if (distVersicolor <= distVirginica) {
            estimacion = "Iris-versicolor";
        } else {
            estimacion = "Iris-virginica";
        }
        //System.out.println(distSetosa+"   "+distVersicolor+"    "+distVirginica+"    "+estimacion);
        return estimacion;
    }

    private Double algoritmoKNN(List<Double> sample, List<Double> tipoFlor) {
        Double res = 0.0;
        for(int i = 0; i < sample.size(); i++) {
            res += (sample.get(i)-tipoFlor.get(i))*(sample.get(i)-tipoFlor.get(i));
        }
        return Math.sqrt(res);
    }

    // MÉTODOS PARA CALCULAR LAS MEDIAS DE LOS VALORES

    private void calcularSetosa(List<Double> lista) {
        longitudSepaloSetosa += lista.get(0);
        anchuraSepaloSetosa += lista.get(1);
        longitudPetaloSetosa += lista.get(2);
        anchuraPetaloSetosa += lista.get(3);
    }

    private void calcularVersicolor(List<Double> lista) {
        longitudSepaloVersicolor += lista.get(0);
        anchuraSepaloVersicolor += lista.get(1);
        longitudPetaloVersicolor += lista.get(2);
        anchuraPetaloVersicolor += lista.get(3);
    }

    private void calcularVirginica(List<Double> lista) {
        longitudSepaloVirginica += lista.get(0);
        anchuraSepaloVirginica += lista.get(1);
        longitudPetaloVirginica += lista.get(2);
        anchuraPetaloVirginica += lista.get(3);
    }

    private void calculaMedias(int cantidadSetosa, int cantidadVersicolor, int cantidadVirginica) {
        longitudSepaloSetosa = longitudSepaloSetosa/cantidadSetosa;
        anchuraSepaloSetosa = anchuraSepaloSetosa/cantidadSetosa;
        longitudPetaloSetosa = longitudPetaloSetosa/cantidadSetosa;
        anchuraPetaloSetosa = anchuraPetaloSetosa/cantidadSetosa;

        longitudSepaloVersicolor = longitudSepaloVersicolor/cantidadVersicolor;
        anchuraSepaloVersicolor = anchuraSepaloVersicolor/cantidadVersicolor;
        longitudPetaloVersicolor = longitudPetaloVersicolor/cantidadVersicolor;
        anchuraPetaloVersicolor = anchuraPetaloVersicolor/cantidadVersicolor;

        longitudSepaloVirginica = longitudSepaloVirginica/cantidadVirginica;
        anchuraSepaloVirginica = anchuraSepaloVirginica/cantidadVirginica;
        longitudPetaloVirginica = longitudPetaloVirginica/cantidadVirginica;
        anchuraPetaloVirginica = anchuraPetaloVirginica/cantidadVirginica;
    }

    // MÉTODO PARA ACTUALIZAR LAS LISTAS QUE USAREMOS EN EL ALGORITMO

    private void actualizaListas() {
        setosa.add(longitudSepaloSetosa); setosa.add(anchuraSepaloSetosa);
        setosa.add(longitudPetaloSetosa); setosa.add(anchuraPetaloSetosa);

        versicolor.add(longitudSepaloVersicolor); versicolor.add(anchuraSepaloVersicolor);
        versicolor.add(longitudPetaloVersicolor); versicolor.add(anchuraPetaloVersicolor);

        virginica.add(longitudSepaloVirginica); virginica.add(anchuraSepaloVirginica);
        virginica.add(longitudPetaloVirginica); virginica.add(anchuraPetaloVirginica);
    }
}
