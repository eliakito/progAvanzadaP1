package paquete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlgoritmoKMeans implements Algorithm <Table, String, List<Double>> {
    // ATRIBUTOS
    int numberClusters;
    int iterations;
    long seed;
    List<Integer> listaRepresentantesGrupo;
    List<Integer> listaIndices;
    ArrayList<Integer> grupos;
    // CONSTRUCTORES
    public AlgoritmoKMeans(){
        numberClusters = 0;
        iterations = 0;
        seed = 0;
        listaRepresentantesGrupo = new ArrayList<Integer>();
        listaIndices = new ArrayList<Integer>();
        grupos = new ArrayList<Integer>();

    }
    public AlgoritmoKMeans(int numberClusters, int iterations, long seed) {
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        listaRepresentantesGrupo = new ArrayList<Integer>();
        listaIndices = new ArrayList<Integer>();
        grupos = new ArrayList<Integer>();
    }

    // MÉTODOS
    @Override
    public void train(Table data) {
        //TODO
        representantesIniciales(data);
        asignarGrupos();
    }

    @Override
    public String estimate(List<Double> sample) {
        //TODO
        return null;
    }

    private void representantesIniciales(Table data) {
       crearListIndices(data);
       representanteAleatorio(listaIndices);
       if(hayRepetidos(listaRepresentantesGrupo))
           representanteAleatorio(listaIndices);
    }

    private void asignarGrupos(Table data) {
        Integer[] grupos = new Integer[data.getColumnAt(0).size()];

    }

    private void calculaDistancias(Table data) {
        for(int i = 0; i < data.getColumnAt(0).size(); i++) {
            //Solucionar metodo en kNN para que se pueda calculas las distancias entre rows.

            for (int j = 0; j< listaRepresentantesGrupo.size(); j++){
                //El metodo comparar distancia es tipo booleano y es true si la distancia es parecida.
                if(compararDistancia(data.getRowAt(0).getData(),data.getRowAt(listaRepresentantesGrupo.get(j)){
                    grupos.add(listaRepresentantesGrupo.get(j));
                }
            }
        }
    }
    private void  representanteAleatorio(List<Integer> listaiIndices){
        Collections.shuffle(listaIndices);
        for(int i = 0; i < numberClusters; i++) {
            listaRepresentantesGrupo.add(listaIndices.get(i));
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
    private boolean compararDistancia(Row a, Row b){
        return false;
    }

    private List<Double> calculoCentroideGrupo(List<Integer> grupos,Table data){
        double x,y,z,t,xf,yf,zf,tf;
        x=y=z=t=0;
        xf=yf=zf=tf=0;
        List<Double> cuentaDatos=new ArrayList<Double>(listaRepresentantesGrupo.size()*4);
        for(int i =0;i< grupos.size();i++){
            List<Double> data1 =data.getRowAt(i).getData();
            x+=data1.get(0);
            y+=data1.get(1);
            z+=data1.get(2);
            t+=data1.get(3);

            xf=cuentaDatos.get(listaRepresentantesGrupo.indexOf(grupos.get(i)));
            yf=cuentaDatos.get(listaRepresentantesGrupo.indexOf(grupos.get(i)+1);
            zf=cuentaDatos.get(listaRepresentantesGrupo.indexOf(grupos.get(i)+2);
            tf=cuentaDatos.get(listaRepresentantesGrupo.indexOf(grupos.get(i)+3);

            cuentaDatos.set(listaRepresentantesGrupo.indexOf(grupos.get(i))*4,x+xf);
            cuentaDatos.set(listaRepresentantesGrupo.indexOf(grupos.get(i))*4+1,x+xf);
            cuentaDatos.set(listaRepresentantesGrupo.indexOf(grupos.get(i))*4+2,x+xf);
            cuentaDatos.set(listaRepresentantesGrupo.indexOf(grupos.get(i))*4+3,x+xf);
            x=y=z=t=0;
            xf=yf=zf=tf=0;
        }
        Row centroide= new Row(cuentaDatos);

        return cuentaDatos;
    }
}
