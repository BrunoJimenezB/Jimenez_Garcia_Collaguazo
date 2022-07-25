package com.example.carritodecomprasvideojuegos.Modelo;

public class ExportarVenta {
    public String getCodigoexportar() {
        return codigoexportar;
    }

    public void setCodigoexportar(String codigoexportar) {
        this.codigoexportar = codigoexportar;
    }

    public String getProductoexportar() {
        return productoexportar;
    }

    public void setProductoexportar(String productoexportar) {
        this.productoexportar = productoexportar;
    }

    public int getCantidadexportar() {
        return cantidadexportar;
    }

    public void setCantidadexportar(int cantidadexportar) {
        this.cantidadexportar = cantidadexportar;
    }

    public float getPrecioexportar() {
        return precioexportar;
    }

    public void setPrecioexportar(float precioexportar) {
        this.precioexportar = precioexportar;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public ExportarVenta(String codigoexportar, String productoexportar, int cantidadexportar, float precioexportar, String stock) {
        this.codigoexportar = codigoexportar;
        this.productoexportar = productoexportar;
        this.cantidadexportar = cantidadexportar;
        this.precioexportar = precioexportar;
        this.stock = stock;
    }

    private String codigoexportar;
    private String productoexportar;
    private int cantidadexportar;
    private float precioexportar;
    private String stock;
}
