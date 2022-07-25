package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Exportar;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelecccionJuego extends AppCompatActivity {
private TextView nombreVideojuego;
private TextView stock;
private TextView precio;
private TextView cantidad;
private ImageView imagenVideo;
private Button botonAgregar;
String codigoVideo="";
String cedulaUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecccion_juego);
        nombreVideojuego = findViewById(R.id.textViewNombreVideoJuego);
        stock = findViewById(R.id.textViewStockVideojuego);
        precio = findViewById(R.id.textViewPrecioVideojuegos);
        cantidad = findViewById(R.id.editTextNumberCantidadVideojuego);
        imagenVideo = findViewById(R.id.imageViewVIdeoJuegoI);
        botonAgregar = findViewById(R.id.buttonAgregar);
        cantidad.setText("1");

        Bundle bundle = getIntent().getExtras(); //pasan los valores del producto selccionado

        if(bundle!=null){

         nombreVideojuego.setText(bundle.getString("nombreVideojuego"));
         stock.setText(bundle.getString("Stock"));
         precio.setText(bundle.getString("Precio"));
         codigoVideo = bundle.getString("codig");
         cedulaUsuario = bundle.getString("ValorCedula");
//         int [] nuevovImagen= bundle.getIntArray("image");
//         imagenVideo.setImageResource(nuevovImagen[0]);
            String texto = bundle.getString("image");

            Picasso.get() //dibuja la imagen
                        .load(texto)
                      .into(imagenVideo);

        }
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(SelecccionJuego.this, cedulaUsuario, Toast.LENGTH_SHORT).show();
                String codigo = codigoVideo;
                String producto = nombreVideojuego.getText().toString();
                int cantidades = Integer.valueOf(cantidad.getText().toString());
                float cantidadesfloat = cantidades;
                float precios = Float.valueOf(precio.getText().toString());
                String stocks = stock.getText().toString();
                if(cantidadesfloat > Integer.valueOf(stocks)){
                    Toast.makeText(SelecccionJuego.this, "La cantidad Supera el stock", Toast.LENGTH_LONG).show();
                    cantidad.setText("1");
                }else if(cantidadesfloat == 0){
                    Toast.makeText(SelecccionJuego.this, "cantidad no admitida", Toast.LENGTH_LONG).show();
                    cantidad.setText("1");
                }
                else{
                PostVideojuegos(cedulaUsuario, codigo, producto,cantidades,precios,stocks);
                Intent intent = new Intent(SelecccionJuego.this, pruebalist.class);
                startActivity(intent);
                //finish();
                }
            }
        });
    }
    public void PostVideojuegos(String cedula, String codigo, String producto, int cantidad, float precio, String stock){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        Exportar exportar  = new Exportar(cedula,codigo,producto,cantidad,precio,stock);
        Call<Exportar> call = servicio.createPost(exportar);
//        Vector vectorVideojuego = new Vector();
//        Vector vectorStock = new Vector();
//        Vector vectorCodigo = new Vector();
//        Vector vectorPrecio = new Vector<>();
//        Vector vectorImagen  = new Vector();
        call.enqueue(new Callback<Exportar>() {
            @Override
            public void onResponse(Call<Exportar> call, Response<Exportar> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
               // List<Videojuego> postVideojuego = response.body();
              //  int i = 0;
                Toast.makeText(SelecccionJuego.this, "Agregado al carrito", Toast.LENGTH_SHORT).show();



                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<Exportar> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });

    }
}