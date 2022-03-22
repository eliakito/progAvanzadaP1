package paquete;

public interface Algorithm <T extends Table, E, S> {
    void train(T data);
    E estimate(S sample);
}
