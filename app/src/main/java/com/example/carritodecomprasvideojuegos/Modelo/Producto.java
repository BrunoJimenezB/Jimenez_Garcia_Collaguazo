package com.example.carritodecomprasvideojuegos.Modelo;

public class Producto {
    public Producto(int stock) {
        Stock = stock;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    private int Stock;

}
