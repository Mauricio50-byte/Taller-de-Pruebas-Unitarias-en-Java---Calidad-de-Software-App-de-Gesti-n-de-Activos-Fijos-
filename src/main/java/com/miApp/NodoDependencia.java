package com.miApp;

public class NodoDependencia {
    public Dependencias dependencia;
    public NodoDependencia prev;
    public NodoDependencia next;

    public NodoDependencia(Dependencias dependencia) {
        this.dependencia = dependencia;
        this.prev = this;
        this.next = this;
    }
}