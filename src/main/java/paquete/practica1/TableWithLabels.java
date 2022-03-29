package paquete.practica1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableWithLabels extends Table {
    // ATRIBUTOS CLASE TABLEWITHLABELS

    // CONSTRUCTORES
    public TableWithLabels() {
        super();
    }
    public TableWithLabels(List<RowWithLabel> data, List<String> headers) {
       super(data,headers);
    }

    // MÉTODOS
    @Override
    public RowWithLabel getRowAt(int row) {
        RowWithLabel fila = new RowWithLabel();
        int contador = 0;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File("iris.csv")));
            String line = null;
            boolean primera = true;
            while ((line = buffer.readLine()) != null) {
                String[] cadena = line.split(",");
                if(primera) {
                    primera = false;
                } else {
                    if(row == contador) {
                        List<Double> data = new ArrayList<Double>();
                        for(int i = 0; i < cadena.length-1; i++) {
                            data.add(Double.parseDouble(cadena[i]));
                        }
                        String label = cadena[cadena.length-1];
                        fila = new RowWithLabel(data, label);
                        break;
                    }
                    contador++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fila;
    }

    @Override
    public List<Double> getColumnAt(int columnNumber) {
        List<Double> lista = new ArrayList<Double>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File("iris.csv")));
            String line = null;
            boolean primera = true;
            while ((line = buffer.readLine()) != null) {
                String[] cadena = line.split(",");
                if(primera) {
                    primera = false;
                } else {
                    lista.add(Double.parseDouble(cadena[columnNumber]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
