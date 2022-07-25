package com.example.carritodecomprasvideojuegos.Modelo;

public class FacturaDetallev2 {


    public FacturaDetallev2(int cantidad, float subtotal, int factura_id, int producto_id) {
        Cantidad = cantidad;
        Subtotal = subtotal;
        Factura_Id = factura_id;
        Producto_Id = producto_id;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float subtotal) {
        Subtotal = subtotal;
    }

    public int getFactura_Id() {
        return Factura_Id;
    }

    public void setFactura_Id(int factura_Id) {
        Factura_Id = factura_Id;
    }

    public int getProducto_Id() {
        return Producto_Id;
    }

    public void setProducto_Id(int producto_Id) {
        Producto_Id = producto_Id;
    }

    private int Cantidad;
    private float Subtotal;
    private int Factura_Id;
    private int Producto_Id;
}
