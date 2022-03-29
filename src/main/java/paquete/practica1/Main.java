package paquete.practica1;

public class Main {
    // CLASE QUE UTILIZO PARA MOSTRAR POR PANTALLA AMBOS FICHEROS
    // Y ASÍ VER CON MAS CLARIDAD COMO TRABAJAR CON ELLOS
    public static void main(String[] args) {
        CSV main = new CSV();
        System.out.println("LEEMOS LA TABLA DEL FICHERO MILES_DOLLARS.CSV");
        System.out.println("");
        Table tabla = main.readTable("miles_dollars.csv");
        System.out.println(tabla.getHeaders().toString());
        for(int i = 0; i < tabla.getColumnAt(0).size(); i++)
            System.out.println(tabla.getRowAt(i).getData().toString());

        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("LEEMOS LA TABLA CON ETIQUETAS DEL FICHERO IRIS.CSV");
        System.out.println("");
        TableWithLabels tableWithLabels = main.readTableWithLabels("iris.csv");
        System.out.println(tableWithLabels.getHeaders().toString());
        for(int i = 0; i < tableWithLabels.getColumnAt(0).size(); i++) {
            System.out.print(tableWithLabels.getRowAt(i).getData().toString());
            System.out.println("      " +tableWithLabels.getRowAt(i).getLabel());
        }
    }
}
