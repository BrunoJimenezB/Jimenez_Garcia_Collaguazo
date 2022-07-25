package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;
import com.example.carritodecomprasvideojuegos.Modelo.VideojuegosRespuesta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VentaInicio extends AppCompatActivity {
private ImageView imagenVideojuego;
private TextView texto;
private RecyclerView recyclerView;
private ListaVideojuegosAdapter listaVideojuegosAdapter;
private static final String TAG = "pokedex";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta_inicio);
//        recyclerView = findViewById(R.id.recyclerViewVideojuegos);
//        recyclerView.setAdapter(listaVideojuegosAdapter);
//        recyclerView.setHasFixedSize(true);
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);

//        imagenVideojuego = findViewById(R.id.imagenViewVideojuego);
//
//        String img = "https://i.pinimg.com/550x/8c/b5/fa/8cb5fab503ed6af77444f8f0d584e3fa.jpg";
//        texto = findViewById(R.id.textViewContenido);

//        getVideojuegos();
    }

//    private void getVideojuegos () {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.101.53:3000/api/games/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
//       // Call<List<Videojuego>> call = servicio.getPost();
//        Call<VideojuegosRespuesta> call = servicio.getPost();
//        call.enqueue(new Callback<VideojuegosRespuesta>() {
//            @Override
//            public void onResponse(Call<VideojuegosRespuesta> call, Response<VideojuegosRespuesta> response) {
//                if(!response.isSuccessful()){
//
//                    return;
//                }
////               List<Videojuego> postVideojuego = response.body();
////
//                VideojuegosRespuesta videojuegosRespuesta = response.body();
//                ArrayList<Videojuego> listaVideojuego  = videojuegosRespuesta.getResults();
//                String contenido = "";
//                for (int i=0; i< listaVideojuego.size(); i++){
//                    Videojuego videojuego = listaVideojuego.get(i);
//                    Log.i(TAG, videojuego.getNombre());
//                }
//              //  listaVideojuegosAdapter.adicionarListaVideojuego(listaVideojuego);
////               texto.setText(postVideojuego.get(0).getImagen());
////               Picasso.get()
////                        .load(texto.getText().toString())
////                        .into(imagenVideojuego);
//            }
//
//            @Override
//            public void onFailure(Call<VideojuegosRespuesta> call, Throwable t) {
//
//            }
//        });
//
//    }
}