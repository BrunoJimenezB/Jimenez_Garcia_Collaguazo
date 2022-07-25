package com.example.carritodecomprasvideojuegos.Modelo;

public class Venta {
    public Venta(int id, String cedulaexportar, String codigoexportar, String productoexportar, int cantidadexportar, float precioexportar, float subtotal, String imagen) {
        this.id = id;
        this.cedulaexportar = cedulaexportar;
        this.codigoexportar = codigoexportar;
        this.productoexportar = productoexportar;
        this.cantidadexportar = cantidadexportar;
        this.precioexportar = precioexportar;
        this.Subtotal = subtotal;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedulaexportar() {
        return cedulaexportar;
    }

    public void setCedulaexportar(String cedulaexportar) {
        this.cedulaexportar = cedulaexportar;
    }

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

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }

    private int id;
    private String cedulaexportar;
    private String codigoexportar;
    private String productoexportar;
    private int cantidadexportar;
    private float precioexportar;
    private float Subtotal;

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    private float Total;
    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    private String stock;
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    private String imagen;

}
