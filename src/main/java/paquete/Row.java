package paquete;

import java.util.*;

public class Row {
    // ATRIBUTOS CLASE ROW
    private List<Double> data;

    // CONSTRUCTORES
    public Row () {
        data = new ArrayList<Double>();
    }
    public Row (List<Double> data) {
        this.data = data;
    }

    // MÉTODOS
    public List<Double> getData() {
        return this.data;
    }
}
