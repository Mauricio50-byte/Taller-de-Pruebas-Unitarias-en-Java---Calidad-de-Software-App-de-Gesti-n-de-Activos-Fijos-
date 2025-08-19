package com.miApp;

public class ListaDobleActivos {
    private NodoActivo head;
    private NodoActivo tail;

    public ListaDobleActivos() {
        this.head = null;
        this.tail = null;
    }

    public void agregar(Activo activo) {
        NodoActivo nuevoNodo = new NodoActivo(activo);
        if (head == null) {
            head = nuevoNodo;
            tail = nuevoNodo;
        } else {
            tail.next = nuevoNodo;
            nuevoNodo.prev = tail;
            tail = nuevoNodo;
        }
    }

    public NodoActivo getHead() {
        return head;
    }

    public NodoActivo getTail() {
        return tail;
    }

    // Método para ordenar la lista por número de activo
    public void ordenarPorNumeroDeActivo() {
        if (head == null || head.next == null) {
            return; // No hay o solo hay un elemento en la lista, ya está "ordenada"
        }
        
        boolean swapped;
        NodoActivo current;
        NodoActivo last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.activo.numeroDeActivo > current.next.activo.numeroDeActivo) {
                    // Intercambiar los activos
                    Activo temp = current.activo;
                    current.activo = current.next.activo;
                    current.next.activo = temp;
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while (swapped);
    }

    // Método para ordenar la lista por documento del responsable
    public void ordenarPorDocumentoDelResponsable() {
        if (head == null || head.next == null) {
            return; // No hay o solo hay un elemento en la lista, ya está "ordenada"
        }
        
        boolean swapped;
        NodoActivo current;
        NodoActivo last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.activo.documentoDelResponsable.compareTo(current.next.activo.documentoDelResponsable) > 0) {
                    // Intercambiar los activos
                    Activo temp = current.activo;
                    current.activo = current.next.activo;
                    current.next.activo = temp;
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while (swapped);
    }

    // Método para ordenar la lista por valor actual
    public void ordenarPorValorActual() {
        if (head == null || head.next == null) {
            return; // No hay o solo hay un elemento en la lista, ya está "ordenada"
        }
        
        boolean swapped;
        NodoActivo current;
        NodoActivo last = null;

        do {
            swapped = false;
            current = head;

            while (current.next != last) {
                if (current.activo.valorActual > current.next.activo.valorActual) {
                    // Intercambiar los activos
                    Activo temp = current.activo;
                    current.activo = current.next.activo;
                    current.next.activo = temp;
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while (swapped);
    }
}
