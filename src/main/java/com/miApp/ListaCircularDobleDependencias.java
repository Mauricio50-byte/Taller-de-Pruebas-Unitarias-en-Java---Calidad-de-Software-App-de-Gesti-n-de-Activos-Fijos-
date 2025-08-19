package com.miApp;

public class ListaCircularDobleDependencias {
    private NodoDependencia head;
    private NodoDependencia tail;

    public ListaCircularDobleDependencias() {
        this.head = null;
        this.tail = null;
    }

    public void agregar(Dependencias dependencia) {
        NodoDependencia nuevoNodo = new NodoDependencia(dependencia);
        if (head == null) {
            head = nuevoNodo;
            tail = nuevoNodo;
        } else {
            tail.next = nuevoNodo;
            nuevoNodo.prev = tail;
            nuevoNodo.next = head;
            head.prev = nuevoNodo;
            tail = nuevoNodo;
        }
    }

    public NodoDependencia getHead() {
        return head;
    }

    public NodoDependencia getTail() {
        return tail;
    }

    
    // Método para ordenar la lista por nombre de dependencia
    public void ordenarPorNombre() {
        if (head == null) {
            return;
        }
        
        NodoDependencia current = head;
        do {
            NodoDependencia temp = current.next;
            while (temp != head) {
                if (current.dependencia.nombre.compareTo(temp.dependencia.nombre) > 0) {
                    // Intercambiar dependencias
                    Dependencias tempDependencia = current.dependencia;
                    current.dependencia = temp.dependencia;
                    temp.dependencia = tempDependencia;
                }
                temp = temp.next;
            }
            current = current.next;
        } while (current != head);
    }

    // Método para ordenar la lista por valor total de activos (ascendente)
    public void ordenarPorValorTotalAscendente() {
        if (head == null) {
            return;
        }
        
        NodoDependencia current = head;
        do {
            NodoDependencia temp = current.next;
            while (temp != head) {
                if (current.dependencia.calcularTotal() > temp.dependencia.calcularTotal()) {
                    // Intercambiar dependencias
                    Dependencias tempDependencia = current.dependencia;
                    current.dependencia = temp.dependencia;
                    temp.dependencia = tempDependencia;
                }
                temp = temp.next;
            }
            current = current.next;
        } while (current != head);
    }

    // Método para buscar una dependencia por nombre
    public Dependencias buscarPorNombre(String nombre) {
        if (head == null) {
            return null;
        }
        
        NodoDependencia current = head;
        do {
            if (current.dependencia.nombre.equals(nombre)) {
                return current.dependencia;
            }
            current = current.next;
        } while (current != head);
        
        return null; // No se encontró ninguna dependencia con el nombre dado
    }

    // Método para buscar una dependencia por valor total de activos
    public Dependencias buscarPorValorTotal(double valorTotal) {
        if (head == null) {
            return null;
        }
        
        NodoDependencia current = head;
        do {
            if (current.dependencia.calcularTotal() == valorTotal) {
                return current.dependencia;
            }
            current = current.next;
        } while (current != head);
        
        return null; // No se encontró ninguna dependencia con el valor total dado
    }

    // Método para buscar una dependencia por un activo específico
    /*public Dependencias buscarPorActivo(int numeroDeActivo) {
        if (head == null) {
            return null;
        }
        
        NodoDependencia current = head;
        do {
            for (Activo activo : current.dependencia.getActivos()) {
                if (activo.numeroDeActivo == numeroDeActivo) {
                    return current.dependencia;
                }
            }
            current = current.next;
        } while (current != head);
        
        return null; // No se encontró ninguna dependencia que contenga el activo con el número dado
    }*/
}

