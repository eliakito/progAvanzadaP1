package paquete.practica2;
import paquete.practica1.Row;
import paquete.practica1.Table;
import paquete.practica3.Distance;
import paquete.practica3.DistanceClient;
import paquete.practica3.EuclideanDistance;

import java.util.*;

public class AlgoritmoKMeans implements Algorithm<Table, String, Row>, DistanceClient {
    // ATRIBUTOS
    int numberClusters;
    int iterations;
    long seed;
    List<Row> listaRepresentantesGrupo;
    List<Integer> listaIndices;
    int [] listaIndiceGrupo;
    Distance distancia;

    // CONSTRUCTORES
    public AlgoritmoKMeans(){
        numberClusters = 0;
        iterations = 0;
        seed = 0;
        listaRepresentantesGrupo = new ArrayList<>(numberClusters);
        listaIndices = new ArrayList<>();
    }
    public AlgoritmoKMeans(int numberClusters, int iterations, long seed, Distance distancia) {
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        listaRepresentantesGrupo = new ArrayList<>(numberClusters);
        listaIndices = new ArrayList<>();
        this.distancia = distancia;
    }

    // MÉTODOS
    @Override
    public void train(Table data)  {
        //TODO
        representantesIniciales(data);
        int [] asignaciones;
        for(int i = 0; i < iterations; i++) {
            asignaciones = asignarGrupos(data);
            calculoCentroideGrupo(asignaciones, data);
        }
    }

    @Override
    public String estimate(Row r) {
        //TODO
        List<Row> listadeRowsestimate = new ArrayList<>();
        listadeRowsestimate.add(r);
        Table talbaAuxiliarEstimate = new Table(listadeRowsestimate,new ArrayList<>());
        int[] grupoMin;
        grupoMin = asignarGrupos(talbaAuxiliarEstimate);
        return "Pertenece al grupo: "+grupoMin[0];
    }

    private void representantesIniciales(Table data) {
        crearListIndices(data);
        representanteAleatorio(listaIndices, data);
        if(hayRepetidos(listaRepresentantesGrupo))
            representanteAleatorio(listaIndices, data);
        representantesGrupoInicialesToString();
    }

    private void  representanteAleatorio(List<Integer> listaIndices, Table data){
        Collections.shuffle(listaIndices,new Random(seed));
        listaRepresentantesGrupo.clear();
        for(int i = 0; i < numberClusters; i++) {
            listaRepresentantesGrupo.add(data.getRowAt(listaIndices.get(i)));
        }
    }
    void representantesGrupoInicialesToString(){
        System.out.println("Estos son los representantes Iniciales: ");
        for(Row o:listaRepresentantesGrupo)
            System.out.println(o.getData().toString());
    }

    //Para crear la tabla de indices una sola vez
    private void crearListIndices(Table data){
        for(int i = 0; i < data.numeroFilas(); i++)
                listaIndices.add(i);
    }
    private boolean hayRepetidos(List<Row> listaRepresentantesGrupo){
        boolean res=false;
        //HASH PARA VER QUE NO HAY REPETIDOS.
        Set<Row> hashHayRepetidos = new HashSet<>();
        for (Row nombre : listaRepresentantesGrupo)
            if (!hashHayRepetidos.add(nombre))
                res=true;
        return res;
    }

    private int [] asignarGrupos(Table data) {
        listaIndiceGrupo= new int[data.numeroFilas()];
        //Para tener distancia inicial para comparar.
        for(int i = 0; i < data.numeroFilas(); i++) {
            double distancia = distanciaEuclide(data.getRowAt(i).getData(), listaRepresentantesGrupo.get(0).getData());
            double distanciaMin = distancia;
            int grupoMin = 0;
            for (int j = 0; j < listaRepresentantesGrupo.size(); j++){
                distancia = distanciaEuclide(data.getRowAt(i).getData(), listaRepresentantesGrupo.get(j).getData());
                if(distancia <=distanciaMin)
                    listaIndiceGrupo[i]=j;
            }
        }
        return listaIndiceGrupo;
    }

    private void calculoCentroideGrupo(int[] asignacionesGrupos,Table data){
        int tamanyoListaRepresentantes = listaRepresentantesGrupo.size();
        List<Integer> tamanyoCentroides = new ArrayList<>();
        for(int c = 0; c < tamanyoListaRepresentantes; c++)
            tamanyoCentroides.add(0);
        for(int i = 0; i < asignacionesGrupos.length; i++) {
            int k = asignacionesGrupos[i];
            listaRepresentantesGrupo.set(k, sumar(data.getRowAt(i).getData(), listaRepresentantesGrupo.get(k).getData()));
            tamanyoCentroides.set(k, tamanyoCentroides.get(k)+1);
        }
        for(int j = 0; j < listaRepresentantesGrupo.size(); j++)
            listaRepresentantesGrupo.set(j, dividir(listaRepresentantesGrupo.get(j).getData(), tamanyoCentroides.get(j)));
    }

    private Row sumar(List<Double> punto, List<Double> representante) {
        List<Double> suma = new ArrayList<>();
        for(int i = 0; i < representante.size(); i++)
            suma.add(punto.get(i) + representante.get(i));
        return new Row(suma);
    }
    private Row dividir(List<Double> representante, int tamanyo) {
        List<Double> division = new ArrayList<>();
        for (Double aDouble : representante) {
            division.add(aDouble / tamanyo);
        }
        return new Row(division);
    }
    @Override
    public void setDistance(Distance distance) {
        this.distancia=distance;
    }
}