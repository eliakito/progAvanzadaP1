package paquete;

import java.util.ArrayList;
import java.util.List;

public class RowWithLabel extends Row {
    // ATRIBUTOS CLASE ROWWITHLABEL
    private String label;


    // CONSTRUCTORES
    public RowWithLabel() {
        super();
        label = null;
    }
    public RowWithLabel(List<Double> data, String label) {
        super(data);
        this.label = label;
    }

    // MÉTODOS
    public String getLabel() {
        return this.label;
    }
}
