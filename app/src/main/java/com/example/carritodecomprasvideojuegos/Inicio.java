package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PostProcessor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inicio extends AppCompatActivity {
ArrayList<Videojuegos> videouegos;
RecyclerView rv1;
private TextView miJson;
private ArrayAdapter<String> adapter;
private ListView vistaVideojuegos;
private String vectorVideojuegos [];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
       // miJson = findViewById(R.id.jsonText);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1 );
      //  rv1 = findViewById(R.id.rv1);
       //getVideojuegos();
        vistaVideojuegos = findViewById(R.id.ListViewVideojuegos);

//
    }
//    private void getVideojuegos () {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://res-movil.herokuapp.com/api/games/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
//        Call<List<Videojuego>> call = servicio.getPost();
//        call.enqueue(new Callback<List<Videojuego>>() {
//            @Override
//            public void onResponse(Call<List<Videojuego>> call, Response<List<Videojuego>> response) {
//                if(!response.isSuccessful()){
//                    miJson.setText("Codigo : "+ response.code());
//                    return;
//                }
//                List<Videojuego> postVideojuego = response.body();
//                int i = 0;
//                Vector vector = new Vector();
//                for (Videojuego post : postVideojuego){
//                    vector.add(String.valueOf(i));
//                    i++;
//                    String contenido = "";
//                   // String imagen = ""
//                    contenido  += "Codigo : "+ post.getCodigo()+"\n";
//                    contenido  += "Nombre : "+ post.getNombre()+"\n";
//                    adapter.add(contenido);
//
//
//                  //  contenido  += "precio : "+ post.getPrecio()+"\n";
////                    contenido  += "imagen" + post.getImagen()+"\n\n";
//                  //  miJson.append(contenido);
//                }
//                Log.i("Genrado", vector.toString());
//                vistaVideojuegos.setAdapter(adapter);
//               // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
//            }
//
//            @Override
//            public void onFailure(Call<List<Videojuego>> call, Throwable t) {
//                 miJson.setText(t.getMessage());
//            }
//        });
//
//    }
}