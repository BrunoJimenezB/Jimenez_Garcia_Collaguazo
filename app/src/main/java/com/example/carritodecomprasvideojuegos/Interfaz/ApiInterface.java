package com.example.carritodecomprasvideojuegos.Interfaz;

import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;
import com.example.carritodecomprasvideojuegos.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
@GET("/producto")
    Call<List<Videojuego>> getPosts();


}
