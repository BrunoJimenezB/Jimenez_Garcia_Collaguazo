package com.example.carritodecomprasvideojuegos.Modelo;

public class ClienteLogin {
    public ClienteLogin(String nombre, String apellido, String direccion, String correo) {
        Nombre = nombre;
        Apellido = apellido;
        Direccion = direccion;
        Correo = correo;
    }

    private String Nombre, Apellido, Direccion, Correo;
}
