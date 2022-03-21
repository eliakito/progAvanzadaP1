package paquete;

public interface Algorithm <T extends Table, E, S> {
    void train(T data) throws InterruptedException;
    E estimate(S sample);
}
