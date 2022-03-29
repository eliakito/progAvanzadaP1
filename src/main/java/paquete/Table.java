package paquete;

import java.io.*;
import java.util.*;

public class Table {
    // ATRIBUTOS CLASE TABLE
    private List<Row> data;
    private List<String> headers;

    // CONSTRUCTORES
    public Table() {
        data = new ArrayList<Row>();
        headers = new ArrayList<String>();
    }
    public Table(List<String> headers) {
        this.headers = headers;
    }
    public Table(List<Row> data, List<String> headers) {
        this.data = data;
        this.headers = headers;
    }

    // MÉTODOS
    public List<String> getHeaders () {
        return this.headers;
    }

        //ESTO ESTÁ MAL, LO VAMOS A SOLUCIONAR MAS ADELANTE, PORQUE NO ESD ESTA PRACTICA PERO FUNCIONA.
    public Row getRowAt(int row) {
        Row fila = new Row();
        int contador = 0;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File("miles_dollars.csv")));
            String line = null;
            boolean primera = true;
            while ((line = buffer.readLine()) != null) {
                String[] cadena = line.split(",");
                if(primera) {
                    primera = false;
                } else {
                    if(row == contador) {
                        List<Double> data = new ArrayList<Double>();
                        data.add(Double.parseDouble(cadena[0])); data.add(Double.parseDouble(cadena[1]));
                        fila = new Row(data);
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
    public List<Double> getColumnAt(int columnNumber) {
        List<Double> lista = new ArrayList<Double>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File("miles_dollars.csv")));
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

    public int numeroFilas(){
        return getColumnAt(0).size();

    }
}

