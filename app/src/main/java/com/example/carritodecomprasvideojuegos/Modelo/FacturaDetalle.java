package com.example.carritodecomprasvideojuegos.Modelo;

public class FacturaDetalle {
    public int getId_factura_detalle() {
        return id_factura_detalle;
    }

    public void setId_factura_detalle(int id_factura_detalle) {
        this.id_factura_detalle = id_factura_detalle;
    }

    public String getId_producto_detalle() {
        return id_producto_detalle;
    }

    public void setId_producto_detalle(String id_producto_detalle) {
        this.id_producto_detalle = id_producto_detalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public FacturaDetalle(int id_factura_detalle, String id_producto_detalle, int cantidad) {
        this.id_factura_detalle = id_factura_detalle;
        this.id_producto_detalle = id_producto_detalle;
        this.cantidad = cantidad;
    }

    private int id_factura_detalle;
    private String id_producto_detalle;
    private int cantidad;

}
