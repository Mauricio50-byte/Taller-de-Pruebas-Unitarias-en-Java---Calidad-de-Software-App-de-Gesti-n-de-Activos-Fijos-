package com.miApp;

public class Dependencias {
    public String nombre;
    public String centroDeCosto;
    public ListaDobleActivos activos;

    public Dependencias(String nombre, String centroDeCosto) {
        this.nombre = nombre;
        this.centroDeCosto = centroDeCosto;
        this.activos = new ListaDobleActivos();
    }

    public void agregarActivo(Activo activo) {
        activos.agregar(activo);
    }

    public ListaDobleActivos getActivos() {
        return activos;
    }

    public double calcularTotal() {
        double total = 0;
        NodoActivo current = activos.getHead();
        while (current != null) {
            total += current.activo.valorActual;
            current = current.next;
        }
        return total;
    }
}
