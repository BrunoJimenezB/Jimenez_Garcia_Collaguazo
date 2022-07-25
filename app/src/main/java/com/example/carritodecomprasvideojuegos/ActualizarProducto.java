package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Exportar;
import com.example.carritodecomprasvideojuegos.Modelo.ExportarVenta;
import com.example.carritodecomprasvideojuegos.Modelo.Venta;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActualizarProducto extends AppCompatActivity {
TextView txtNombreVideojuego;
TextView txtPrecio;
TextView txtStock;
EditText editTextCantidad;
ImageView imagenVentaV;
Button btnEliminarVenta;
Button btnActualizar;
Button btnCambiarProducto;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_producto);
        txtNombreVideojuego = findViewById(R.id.textViewNombreVentaV);
        txtPrecio = findViewById(R.id.textViewPrecioVentaV);
        txtStock = findViewById(R.id.textViewStockVentaV);
        imagenVentaV = findViewById(R.id.imageViewVentaV);
        editTextCantidad = findViewById(R.id.editTextCantidadVentaV);
        btnEliminarVenta = findViewById(R.id.buttonElimiinarVentaV);
        btnActualizar = findViewById(R.id.buttonActualizarVentaV);
        btnCambiarProducto = findViewById(R.id.buttonCambiarProductoV);
         bundle = getIntent().getExtras();

        if(bundle!=null){

            txtNombreVideojuego.setText(bundle.getString("nombreVideojuego"));
            txtStock.setText(bundle.getString("StockVenta"));
            txtPrecio.setText(bundle.getString("PrecioVenta"));
            editTextCantidad.setText(bundle.getString("CantidadVenta"));
            String img = bundle.getString("ImagenVenta");

            Picasso.get()
                    .load(img)
                    .into(imagenVentaV);

        }
        btnCambiarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(ActualizarProducto.this, ProductoCambio.class);
//                int valorId =  Integer.valueOf(bundle.get("idVenta").toString());
                 intent.putExtra("idVentaProducto", bundle.get("idVenta").toString());
                intent.putExtra("imgVentaProducto", bundle.getString("ImagenVenta"));
                 startActivity(intent);
         //       Toast.makeText(getApplicationContext(), bundle.get("idVenta").toString(), Toast.LENGTH_SHORT).show();
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inicio método
                if(Float.valueOf(editTextCantidad.getText().toString()) > Float.valueOf(txtStock.getText().toString())){
                    Toast.makeText(ActualizarProducto.this, "La cantidad Supera el stock", Toast.LENGTH_LONG).show();
                    editTextCantidad.setText("1");
                }else if(Float.valueOf(editTextCantidad.getText().toString()) ==0 ){
                    Toast.makeText(ActualizarProducto.this, "cantidad no admitida", Toast.LENGTH_LONG).show();
                    editTextCantidad.setText("1");
                } else{

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://res-movil.herokuapp.com/api/games/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
                String codigo = bundle.getString("codigoVenta");
                String producto = bundle.getString("NombreVideojuego");
                int cantidad = Integer.valueOf(editTextCantidad.getText().toString());
                float precio = Float.valueOf(bundle.getString("PrecioVenta"));
                String stock = bundle.getString("StockVenta");
                int valorId =  Integer.valueOf(bundle.get("idVenta").toString());
                Toast.makeText(ActualizarProducto.this, producto, Toast.LENGTH_SHORT).show();
                ExportarVenta exportar = new ExportarVenta(codigo, producto, cantidad, precio,stock);

                Call<ExportarVenta> call = servicio.updateVentaOne(valorId, exportar);
                call.enqueue(new Callback<ExportarVenta>() {
                    @Override
                    public void onResponse(Call<ExportarVenta> call, Response<ExportarVenta> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        ExportarVenta postVenta = response.body();

                        Toast.makeText(getApplicationContext(), "Producto Actualizado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), VistaCarritoCompras.class);
                        startActivity(intent);
                      //  finish();

                    }

                    @Override
                    public void onFailure(Call<ExportarVenta> call, Throwable t) {
                        //  miJson.setText(t.getMessage());
                    }
                });}
            }
        });
        btnEliminarVenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inicio metodo
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://res-movil.herokuapp.com/api/games/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
                int valorId =  Integer.valueOf(bundle.get("idVenta").toString());
                Call<Venta> call = servicio.deleteVentaOne(valorId);
                call.enqueue(new Callback<Venta>() {
                    @Override
                    public void onResponse(Call<Venta> call, Response<Venta> response) {
                        if(!response.isSuccessful()){
                            return;
                        }
                        Venta postVenta = response.body();

                      Toast.makeText(getApplicationContext(), "Producto Eliminado", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(getApplicationContext(), VistaCarritoCompras.class);
                      startActivity(intent);
                       // finish();
                    }

                    @Override
                    public void onFailure(Call<Venta> call, Throwable t) {
                        //  miJson.setText(t.getMessage());
                    }
                });


            }
        });
            }


    }
