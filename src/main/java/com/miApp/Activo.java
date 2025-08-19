package com.miApp;

public class Activo {
    public int numeroDeActivo;
    public String descripcion;
    public FechaDeCompra fechaDeCompra;
    public String documentoDelResponsable;
    public String nombreDelResponsable;
    public double valorHistorico;
    public double valorActual;
    public String ubicacionEspecifica;

    public Activo(int numeroDeActivo, String descripcion, FechaDeCompra fechaDeCompra, String documentoDelResponsable, String nombreDelResponsable, double valorHistorico, double valorActual, String ubicacionEspecifica) {
        this.numeroDeActivo = numeroDeActivo;
        this.descripcion = descripcion;
        this.fechaDeCompra = fechaDeCompra;
        this.documentoDelResponsable = documentoDelResponsable;
        this.nombreDelResponsable = nombreDelResponsable;
        this.valorHistorico = valorHistorico;
        this.valorActual = valorActual;
        this.ubicacionEspecifica = ubicacionEspecifica;
    }

    public int getNumeroDeActivo() {
        return numeroDeActivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public FechaDeCompra getFechaDeCompra() {
        return fechaDeCompra;
    }

    public String getDocumentoDelResponsable() {
        return documentoDelResponsable;
    }

    public String getNombreDelResponsable() {
        return nombreDelResponsable;
    }

    public double getValorHistorico() {
        return valorHistorico;
    }

    public double getValorActual() {
        return valorActual;
    }

    public String getUbicacionEspecifica() {
        return ubicacionEspecifica;
    }

}
