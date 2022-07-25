package com.example.carritodecomprasvideojuegos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Factura;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VistaCompras extends AppCompatActivity {
ListView listViewCompras;
Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_compras);
        listViewCompras = findViewById(R.id.lvCompras);
        Toolbar toolbar = findViewById(R.id.toolbarMenu2);
        setSupportActionBar(toolbar);
        MostrarCompras();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.menuRegresar)
        {
            Intent intent = new Intent(this, pruebalist.class);
            startActivity(intent);
            //finish();
            //Toast.makeText(this, "Presiono en compras: ", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }



    public void MostrarCompras(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
       // bundle = getIntent().getExtras();
       //Toast.makeText(this, "ok"+MyApplication.valorInicial, Toast.LENGTH_SHORT).show();

        Call<List<Factura>> call = servicio.getCompras(MyApplication.valorInicial);
        Vector vectorIdFactura = new Vector();
        Vector vectorCedula = new Vector();
        Vector vectorFecha = new Vector();
        Vector vectorImagen  = new Vector();

        call.enqueue(new Callback<List<Factura>>() {
            @Override
            public void onResponse(Call<List<Factura>> call, Response<List<Factura>> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                List<Factura> FacturaVentas = response.body();
                int i = 0;

                for (Factura post : FacturaVentas){
                    vectorIdFactura.add(post.getId_factura());
                    //vectorStock.add(post.getCodigo())
                    vectorCedula.add(post.getCedula_clienteFactura());
                    vectorFecha.add(post.getFecha());
                    vectorImagen.add("https://us.123rf.com/450wm/dacianlogan/dacianlogan1712/dacianlogan171200010/91897814-%C3%ADcone-de-recibo-de-vendas-de-vetor.jpg?ver=6");
                   // vectorStock.add(post.getStock());
                   // vectorImagen.add(post.getImagen());
//                    i++;
//                    String contenido = "";
//                    // String imagen = ""
//                    contenido  += "Codigo : "+ post.getCodigo()+"\n";
//                    contenido  += "Nombre : "+ post.getNombre()+"\n";
                    //   adapter.add(contenido);


                    //  contenido  += "precio : "+ post.getPrecio()+"\n";
//                    contenido  += "imagen" + post.getImagen()+"\n\n";
                    //  miJson.append(contenido);
                }
                FacturaAdapter programAdapter = new FacturaAdapter(getApplicationContext(), vectorIdFactura, vectorImagen, vectorCedula, vectorFecha);
                listViewCompras.setAdapter(programAdapter);
          //      Log.i("Genrado", vectorVideojuego.toString());

                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Factura>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });




    }

}