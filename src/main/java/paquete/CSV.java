package paquete;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    public Table readTable(String nombreFichero) {
        Table tabla = new Table();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File(nombreFichero)));
            String line = null;
            boolean primera = true;
            List<String> headers = new ArrayList<String>();
            List<Row> data = new ArrayList<Row>();
            while ((line = buffer.readLine()) != null) {
                String[] cadena = line.split(",");
                // podría hacerse fuera del bucle,
                // leer la primera línea y después empezar el fichero en la siguiente
                // pero así lo vimos mas claro de primeras
                if(primera) {
                    for(String palabra : cadena) {
                        headers.add(palabra);
                    }
                    primera = false;
                } else {
                    List<Double> lista = new ArrayList<Double>();
                    for(String numero : cadena) {
                        lista.add(Double.parseDouble(numero));
                    }
                    Row row = new Row(lista);
                    data.add(row);
                }
            }
            tabla = new Table(data, headers);
            return tabla;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tabla;
    }

    public TableWithLabels readTableWithLabels(String nombreFichero) {
        TableWithLabels tableWithLabels = new TableWithLabels();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(new File(nombreFichero)));
            String line = null;
            boolean primera = true;
            List<String> headers = new ArrayList<String>();
            List<RowWithLabel> datos = new ArrayList<RowWithLabel>();
            while ((line = buffer.readLine()) != null) {
                String[] cadena = line.split(",");
                if(primera) {
                    for(String palabra : cadena) {
                        headers.add(palabra);
                    }
                    primera = false;
                } else {
                    List<Double> data = new ArrayList<Double>();
                    for(int i = 0; i < cadena.length-1; i++) {
                        data.add(Double.parseDouble(cadena[i]));
                    }
                    String label = cadena[cadena.length-1];
                    RowWithLabel rowWithLabel = new RowWithLabel(data, label);
                    datos.add(rowWithLabel);
                }
            }
            tableWithLabels = new TableWithLabels(datos, headers);
            return tableWithLabels;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tableWithLabels;
    }
}
