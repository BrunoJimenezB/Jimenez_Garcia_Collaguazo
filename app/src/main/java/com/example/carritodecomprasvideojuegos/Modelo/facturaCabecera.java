package com.example.carritodecomprasvideojuegos.Modelo;

public class facturaCabecera {

    public facturaCabecera(float subtotal, float iva, float total) {
        Subtotal = subtotal;
        Iva = iva;
        Total = total;
    }

    private float  Subtotal;
                private float  Iva;
               private float Total;

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }

    public float getIva() {
        return Iva;
    }

    public void setIva(float iva) {
        Iva = iva;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }
}
