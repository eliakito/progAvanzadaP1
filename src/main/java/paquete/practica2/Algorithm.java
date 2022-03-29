package paquete.practica2;

import paquete.practica1.Table;

public interface Algorithm <T extends Table, E, S> {
    void train(T data);
    E estimate(S sample);
}
