package com.example.carritodecomprasvideojuegos.Modelo;

public class Totales {

    public Totales(float total, float iva, float totalconIva) {
        Total = total;
        Iva = iva;
        TotalconIva = totalconIva;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public float getIva() {
        return Iva;
    }

    public void setIva(float iva) {
        Iva = iva;
    }

    public float getTotalconIva() {
        return TotalconIva;
    }

    public void setTotalconIva(float totalconIva) {
        TotalconIva = totalconIva;
    }

    private float Total;
    private float Iva;
    private float TotalconIva;
}
