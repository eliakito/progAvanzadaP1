package paquete;

import java.util.*;

public class AlgoritmoKMeans implements Algorithm <Table, String, Row> {
    // ATRIBUTOS
    int numberClusters;
    int iterations;
    long seed;
    List<Row> listaRepresentantesGrupo;
    List<Integer> listaIndices;
    int [] listaIndiceGrupo;
    List<List<Double>> listaSumas;
    // CONSTRUCTORES
    public AlgoritmoKMeans(){
        numberClusters = 0;
        iterations = 0;
        seed = 0;
        listaRepresentantesGrupo = new ArrayList<Row>(numberClusters);
        listaIndices = new ArrayList<>();

    }
    public AlgoritmoKMeans(int numberClusters, int iterations, long seed) {
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        listaRepresentantesGrupo = new ArrayList<Row>(numberClusters);
        listaIndices = new ArrayList<>();
        listaIndiceGrupo= new int[150];


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
        IndicesToString();
    }

    @Override
    public String estimate(Row r) {
        //TODO
        Double distancia = distanciaEuclide(r.getData(), listaRepresentantesGrupo.get(0).getData());
        Double distanciaMin = distancia;
        Integer grupoMin = 0;
        for(int i = 0; i < listaRepresentantesGrupo.size(); i++) {
            distancia = distanciaEuclide(r.getData(), listaRepresentantesGrupo.get(i).getData());
            if(distancia >= distanciaMin) {
                distanciaMin = distancia;
                grupoMin = i;
            }
        }
        return "Pertenece al grupo: "+grupoMin;
    }

    private void representantesIniciales(Table data) {
        crearListIndices(data);
        representanteAleatorio(listaIndices, data);
        if(hayRepetidos(listaRepresentantesGrupo)){
            representanteAleatorio(listaIndices, data);
        System.out.println("No me jodas rey que ha salido uno repetido");
        }
        representantesGrupoToString();

    }

    private void  representanteAleatorio(List<Integer> listaIndices, Table data){

        Collections.shuffle(listaIndices);
        //Collections.shuffle(listaIndices,new Random(seed));
        //CLEAR SI HAY REPETIDOS TENER LISTA VACIA DE NUEVO.
        listaRepresentantesGrupo.clear();
        for(int i = 0; i < numberClusters; i++) {
            listaRepresentantesGrupo.add(data.getRowAt(listaIndices.get(i)));
        }
    }
    void representantesGrupoToString(){
        for(Row o:listaRepresentantesGrupo)
            System.out.println("Estos son los representantes "+o.getData().toString());
    }

    //Para crear la tabla una sola vez
    private void crearListIndices(Table data){
        for(int i = 0; i < data.getColumnAt(0).size(); i++)
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
       // List<Integer> asignacionesGrupos= new ArrayList<>();

        for(int i = 0; i < data.getColumnAt(0).size(); i++) {
            Double distancia = distanciaEuclide(data.getRowAt(i).getData(), listaRepresentantesGrupo.get(0).getData());
            Double distanciaMin = distancia;
            Integer grupoMin = 0;
            for (int j = 0; j < listaRepresentantesGrupo.size(); j++){
                distancia = distanciaEuclide(data.getRowAt(i).getData(), listaRepresentantesGrupo.get(j).getData());
                if(distancia <=distanciaMin) {
                    grupoMin = j;
                    listaIndiceGrupo[i]=j;
                }
            }
        }
        return listaIndiceGrupo;
    }

    void IndicesToString(){
        for(int o:listaIndiceGrupo)
            System.out.println("Grupo: "+o);
    }

    private void calculoCentroideGrupo(int[] asignacionesGrupos,Table data){
        listaSumas=new ArrayList<List<Double>>(numberClusters);
        int[]tamañosGrupos=new int[numberClusters];

        for(int i = 0; i < listaIndiceGrupo.length; i++) {
            Integer k = listaIndiceGrupo[i];
            listaSumas.set(k, sumar(listaSumas.get(k),i,data));
        }
        for(int j = 0; j < listaRepresentantesGrupo.size(); j++) {
            listaRepresentantesGrupo.set(j, null);
        }
    }

    private List<Double> sumar (List<Double> recopilacion,int indice, Table data) {
        List<Double> loquellevamos= listaSumas.get(indice);
        List<Double> loqueSumo= data.getRowAt(indice).getData();
        for(int i = 0; i < loquellevamos.size(); i++) {
            loquellevamos.set(i,loquellevamos.get(i) + loqueSumo.get(i));
        }
        return loquellevamos;
    }
    private Row dividir(List<Double> representante, int tamanyo) {
        List<Double> division = new ArrayList<>();
        for(int i = 0; i < representante.size(); i++) {
            division.add(representante.get(i)/tamanyo);
        }
        return new Row(division);
    }


    private Double distanciaEuclide(List<Double> sample, List<Double> representante) {
        Double res = 0.0;
        for(int i = 0; i < representante.size(); i++) {
            res += Math.pow((sample.get(i)-representante.get(i)),2);
        }
        return Math.sqrt(res);
    }
}