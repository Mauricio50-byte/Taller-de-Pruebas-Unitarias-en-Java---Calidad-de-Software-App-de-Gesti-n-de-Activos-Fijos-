package com.miApp;

public class NodoActivo {
    public Activo activo;
    public NodoActivo prev;
    public NodoActivo next;

    public NodoActivo(Activo activo) {
        this.activo = activo;
        this.prev = null;
        this.next = null;
    }
}
