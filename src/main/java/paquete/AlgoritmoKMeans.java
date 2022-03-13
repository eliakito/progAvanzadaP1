package paquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoKMeans implements Algorithm <Table, String, List<Double>> {
    // ATRIBUTOS
    int numberClusters;
    int iterations;
    long seed;
    List<Integer> listaRepresentantes;
    List<Integer> listaIndices;
    ArrayList<Integer> grupos;
    // CONSTRUCTORES
    public AlgoritmoKMeans(){
        numberClusters = 0;
        iterations = 0;
        seed = 0;
        listaRepresentantes = new ArrayList<Integer>();
        listaIndices = new ArrayList<Integer>();
        grupos = new ArrayList<Integer>();

    }
    public AlgoritmoKMeans(int numberClusters, int iterations, long seed) {
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        listaRepresentantes = new ArrayList<Integer>();

    }

    // MÉTODOS
    @Override
    public void train(Table data) {
        representantesIniciales(data);
        asignarGrupos();
    }

    @Override
    public String estimate(List<Double> sample) {
        return null;
    }

    private void representantesIniciales(Table data) {
       crearListIndices(data);
       representanteAleatorio(listaIndices);
       if(hayRepetidos(listaRepresentantes))
           representanteAleatorio(listaIndices);
    }

    private void asignarGrupos(Table data) {
        Integer[] grupos = new Integer[data.getColumnAt(0).size()];

    }

    private void calculaDistancias(Table data) {
        for(int i = 0; i < data.getColumnAt(0).size(); i++) {
            //Solucionar metodo en kNN para que se pueda calculas las distancias entre rows.

            for (int j=0;i<listaRepresentantes.size();i++){
                //El metodo comparar distancia es tipo booleano.
                if(compararDistancia(data.getRowAt(0).getData(),data.getRowAt(listaRepresentantes.get(j)){
                    grupos.add(listaRepresentantes.get(j));

                }

            }

        }
    }
    private void  representanteAleatorio(List<Integer> listaiIndices){
        Collections.shuffle(listaIndices);
        for(int i = 0; i < numberClusters; i++) {
            listaRepresentantes.add(listaIndices.get(i));
        }
    }
    //Para crear la tabla una sola vez
    private void crearListIndices(Table data){
        for(int i = 0; i < data.getColumnAt(0).size(); i++) {
            listaIndices.add(i);
        }
    }
    private boolean hayRepetidos(List<Integer> listaRepresentantes){
        for(Integer index: listaRepresentantes){
            if (listaRepresentantes.contains(index))
                return true;
        }
        return false;
    }

    //ESTA CLASE ES PORQUE ME DA ANSIEDAD QUE APAREZCA EL NOMBRE EN ROJO. PERO TENEMOS QUE CREARLO EN KNN.

    private List<Integer> calculoCentroideGrupo(List<Integer> grupos,Table data){
        double x,y,z,t,xf,yf,tf;
        x=y=z=t;
        List<Integer> listaCentroide=new ArrayList<>(listaRepresentantes.size());
        for(int i =0;i< grupos.size();i++){
            List<Double> data1 =data.getRowAt(i).getData();
            x+=data1.get(0);
            y+=data1.get(1);
            z+=data1.get(2);
            t+=data1.get(3);
            centroide=1+1;
         listaCentroide.add(i,(int)centroide);
        }
        Row centroide= new Row(listaCentroide);

        return listaCentroide;
    }
    private boolean compararDistancia(Row a, Row b){
        return false;
    }
}
