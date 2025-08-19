package com.miApp;

public class FechaDeCompra {
    public int año;
    public int mes;
    public int dia;

    public FechaDeCompra(int año, int mes, int dia) {
        this.año = año;
        this.mes = mes;
        this.dia = dia;
    }

    public int getAño() {
        return año;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FechaDeCompra other = (FechaDeCompra) obj;
        return año == other.año && mes == other.mes && dia == other.dia;
    }
    
}
