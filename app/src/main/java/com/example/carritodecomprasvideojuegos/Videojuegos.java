package com.example.carritodecomprasvideojuegos;

public class Videojuegos {
    public Videojuegos(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    private String nombre;
 private String precio;

 public String getNombre(){
     return nombre;
 }
 public String getPrecio(){
     return precio;
 }
}
