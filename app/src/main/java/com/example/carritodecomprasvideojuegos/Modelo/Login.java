package com.example.carritodecomprasvideojuegos.Modelo;

public class Login {



    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getPassword() {
        return Contraseña;
    }

    public void setPassword(String password) {
        this.Contraseña = password;
    }

   
    private String Usuario;

    public Login(String usuario, String password, String Correo) {
        this.Usuario = usuario;
        this.Contraseña = password;
        this.Correo = Correo;
        
    }

    private String Contraseña;
    private String Correo;
    
}
