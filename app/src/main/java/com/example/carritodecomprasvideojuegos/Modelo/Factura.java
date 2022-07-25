package com.example.carritodecomprasvideojuegos.Modelo;

import java.util.Date;

public class Factura {
    private int id_factura;

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public String getCedula_clienteFactura() {
        return cedula_clienteFactura;
    }

    public void setCedula_clienteFactura(String cedula_clienteFactura) {
        this.cedula_clienteFactura = cedula_clienteFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    private String cedula_clienteFactura;
    private Date fecha;
}
