package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Exportar;
import com.example.carritodecomprasvideojuegos.Modelo.ExportarVenta;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VistaCambioProducto extends AppCompatActivity {
ImageView ProductoAnteriorImg;
ImageView ProductoActualImg;
    ImageView ChangedImg;
TextView txtProducto;
TextView txtStock;
TextView txtPrecio;
EditText  editTextCantidad;
String codigo;
String producto;
int cantidad;

String stock;
String imgAnterior;
String imgActual;
int idVentaCambioAnterior;
Button btnActualizar;
    float precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_cambio_producto);
        ProductoAnteriorImg = findViewById(R.id.imageViewImgAnterior);
        ProductoActualImg = findViewById(R.id.imageViewImgActual);
        txtProducto = findViewById(R.id.textViewProductoActualCambio);
        txtStock = findViewById(R.id.textViewStockActualCambio);
        txtPrecio = findViewById(R.id.textViewPrecioActualCambio);
        editTextCantidad = findViewById(R.id.editTextCantidadCambioProducto);
        ChangedImg = findViewById(R.id.imageViewChanged);
        ChangedImg.setImageResource(R.drawable.recharge);
        btnActualizar = findViewById(R.id.buttonActualizarPCambio);
        Bundle bundle = getIntent().getExtras();
        editTextCantidad.setText("1");
        if (bundle != null){
            imgAnterior = bundle.getString("imgAnterior");
            idVentaCambioAnterior = bundle.getInt("idVentaAnterior");
            imgActual = bundle.getString("imageActual");
            codigo = bundle.getString("codigoProductoActual");
            producto = bundle.getString("nombreProductoActual");

            precio = Float.valueOf(bundle.getString("PrecioProductoActual"));
          //  precio = bundle.getFloat("PrecioProductoActual");
            stock = bundle.getString("StockProductoActual");
            //Cargar datos
            txtProducto.setText(producto);
            txtStock.setText(stock);
            txtPrecio.setText(String.valueOf(bundle.getString("PrecioProductoActual")));
           // codigoVideo = bundle.getString("codig");
//         int [] nuevovImagen= bundle.getIntArray("image");
//         imagenVideo.setImageResource(nuevovImagen[0]);


            Picasso.get()
                    .load(imgAnterior)
                    .resize(150,200)
                    .into(ProductoAnteriorImg);
            Picasso.get()
                    .load(imgActual)
                    .resize(150,200)
                    .into(ProductoActualImg);

            //Accion Boton
            btnActualizar.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    if(Float.valueOf(editTextCantidad.getText().toString()) > Float.valueOf(txtStock.getText().toString())){
                        Toast.makeText(VistaCambioProducto.this, "La cantidad Supera el stock", Toast.LENGTH_LONG).show();
                        editTextCantidad.setText("1");
                    }else if(Float.valueOf(editTextCantidad.getText().toString()) ==0 ) {
                        Toast.makeText(VistaCambioProducto.this, "cantidad no admitida", Toast.LENGTH_LONG).show();
                        editTextCantidad.setText("1");
                    }else{
                    //Inicio metodo
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://res-movil.herokuapp.com/api/games/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
//                    String codigo = bundle.getString("codigoVenta");
//                    String producto = bundle.getString("nombreVideojuego");
//                    int cantidad = Integer.valueOf(editTextCantidad.getText().toString());
                    cantidad = Integer.valueOf(editTextCantidad.getText().toString());
//                    String stock = bundle.getString("StockVenta");
                    int valorId =  Integer.valueOf(bundle.get("idVentaAnterior").toString());
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
                        }

                        @Override
                        public void onFailure(Call<ExportarVenta> call, Throwable t) {
                            //  miJson.setText(t.getMessage());
                        }
                    });}
                }
            });
        }
    }
}