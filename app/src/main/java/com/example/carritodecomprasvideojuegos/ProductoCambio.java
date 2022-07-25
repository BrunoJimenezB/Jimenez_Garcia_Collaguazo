package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoCambio extends AppCompatActivity {
ListView lvlCambioProducto;
String idVentaP;
String imgProductoAnterio;
Spinner spinner2;
String [] categoria = { "Accion", "Aventura", "Estrategia", "Disparos", "Rol", "Terror", "Survival"   };
    int valorCategoria = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_cambio);
        lvlCambioProducto = findViewById(R.id.lvVistaProductoCambio);
        nuevoVectorVideoJuegos(valorCategoria);
        Bundle bundle = getIntent().getExtras();
        spinner2= findViewById(R.id.sp02);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item , categoria );
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int id = adapterView.getSelectedItemPosition();
                if(id == 0){
                    valorCategoria = 1;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 1){
                    valorCategoria = 2;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 2){
                    valorCategoria = 3;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 3){
                    valorCategoria = 4;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 4){
                    valorCategoria = 5;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 5){
                    valorCategoria = 6;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 6){
                    valorCategoria = 7;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(bundle!=null){
            imgProductoAnterio = bundle.getString("imgVentaProducto");
            idVentaP = bundle.getString("idVentaProducto");

        }

    }
    public void nuevoVectorVideoJuegos(int valorCategoria){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        Call<List<Videojuego>> call = servicio.getPost(valorCategoria);
        Vector vectorVideojuego = new Vector();
        Vector vectorStock = new Vector();
        Vector vectorCodigo = new Vector();
        Vector vectorPrecio = new Vector<>();
        Vector vectorImagen  = new Vector();
        call.enqueue(new Callback<List<Videojuego>>() {
            @Override
            public void onResponse(Call<List<Videojuego>> call, Response<List<Videojuego>> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                List<Videojuego> postVideojuego = response.body();
                int i = 0;

                for (Videojuego post : postVideojuego){
                    vectorVideojuego.add(post.getNombre());
                    //vectorStock.add(post.getCodigo())
                    vectorCodigo.add(post.getCodigo());
                    vectorPrecio.add(post.getPrecio());
                    vectorStock.add(post.getStock());
                    vectorImagen.add(post.getImagen());
                    i++;
                    String contenido = "";
                    // String imagen = ""
                    contenido  += "Codigo : "+ post.getCodigo()+"\n";
                    contenido  += "Nombre : "+ post.getNombre()+"\n";
                    //   adapter.add(contenido);


                    //  contenido  += "precio : "+ post.getPrecio()+"\n";
//                    contenido  += "imagen" + post.getImagen()+"\n\n";
                    //  miJson.append(contenido);
                }
                ProductoCambioAdapter programAdapter = new ProductoCambioAdapter(getApplicationContext(), vectorVideojuego, vectorImagen, vectorStock, vectorCodigo, vectorPrecio, idVentaP, imgProductoAnterio);
                lvlCambioProducto.setAdapter(programAdapter);
                Log.i("Genrado", vectorVideojuego.toString());

                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Videojuego>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });

    }

}