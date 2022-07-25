package com.example.carritodecomprasvideojuegos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.FacturaVenta;
import com.example.carritodecomprasvideojuegos.Modelo.Totales;
import com.example.carritodecomprasvideojuegos.Modelo.Venta;
import com.example.carritodecomprasvideojuegos.Modelo.VentaFactura;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VistaComprasId extends AppCompatActivity {
    ListView listViewComprasId;
    Bundle bundle;
    TextView txtTotales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_compras_id);
        listViewComprasId = findViewById(R.id.lvComprasId);
        Toolbar toolbar = findViewById(R.id.toolbarMenuComprasId);
        setSupportActionBar(toolbar);
        txtTotales = findViewById(R.id.txtTotalView);
        bundle = getIntent().getExtras();
        MostrarComprasId();
        calculoTotales();

    }

    public void MostrarComprasId() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio = retrofit.create(ServiciosApi.class);
        Call<List<FacturaVenta>> call = servicio.getComprasId(Integer.valueOf(bundle.get("idFactura").toString()));
        Vector vectorid = new Vector();
        Vector vectorCedula = new Vector();
        Vector vectorcodigo = new Vector();
        Vector vectorproducto = new Vector();
        Vector vectorcantidad = new Vector();
        Vector vectorSubtotal = new Vector<>();
        Vector vectorImagen = new Vector();
        Vector vectorprecio = new Vector();
        call.enqueue(new Callback<List<FacturaVenta>>() {
            @Override
            public void onResponse(Call<List<FacturaVenta>> call, Response<List<FacturaVenta>> response) {
                if (!response.isSuccessful()) {
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                List<FacturaVenta> postVideojuego = response.body();
                int i = 0;

                for (FacturaVenta post : postVideojuego) {
                    vectorid.add(9);
                    vectorCedula.add("2200081442");
                    vectorSubtotal.add(post.getTotal());
                    vectorcodigo.add(post.getCodigo());
                    //vectorStock.add(post.getCodigo())
                    vectorproducto.add(post.getProducto());
                    vectorprecio.add(post.getPrecio());
                    vectorcantidad.add(post.getCantidad());
                    vectorImagen.add(post.getImagen());
                    i++;
                    String contenido = "";
                    // String imagen = ""
//                    contenido  += "Codigo : "+ post.getCodigo()+"\n";
//                    contenido  += "Nombre : "+ post.getNombre()+"\n";
                    //   adapter.add(contenido);


                    //  contenido  += "precio : "+ post.getPrecio()+"\n";
//                    contenido  += "imagen" + post.getImagen()+"\n\n";
                    //  miJson.append(contenido);
                }
                VentasAdapter programAdapter = new VentasAdapter(getApplicationContext(), vectorid, vectorImagen, vectorCedula, vectorcodigo, vectorproducto, vectorcantidad, vectorprecio, vectorSubtotal);
                listViewComprasId.setAdapter(programAdapter);
                //   Log.i("Genrado", vectorVideojuego.toString());

                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<FacturaVenta>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });
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
            Intent intent = new Intent(this, VistaCompras.class);
            startActivity(intent);
         //   finish();
            //Toast.makeText(this, "Presiono en compras: ", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void calculoTotales() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio = retrofit.create(ServiciosApi.class);
        //Integer.valueOf(bundle.get("idFactura").toString())

        Call<Totales> call = servicio.getTotalId(Integer.valueOf(bundle.get("idFactura").toString()));
//        Vector vectorid = new Vector();
//        Vector vectorCedula = new Vector();
//        Vector vectorcodigo = new Vector();
//        Vector vectorproducto= new Vector();
//        Vector vectorcantidad = new Vector();
//        Vector vectorSubtotal= new Vector<>();
//        Vector vectorImagen  = new Vector();
//        Vector vectorprecio = new Vector();
        call.enqueue(new Callback<Totales>() {
            @Override
            public void onResponse(Call<Totales> call, Response<Totales> response) {
                if (!response.isSuccessful()) {
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                Totales post = response.body();
                int i = 0;


//                    vectorid.add(post.getId());
//                    vectorCedula.add(post.getCedulaexportar());
//                    vectorSubtotal.add(post.getSubtotal());
//                    vectorcodigo.add(post.getCodigoexportar());
//                    //vectorStock.add(post.getCodigo())
//                    vectorproducto.add(post.getProductoexportar());
//                    vectorprecio.add(post.getPrecioexportar());
//                    vectorcantidad.add(post.getCantidadexportar());
//                    vectorImagen.add(post.getImagen());
                    i++;
                    String contenido = "";
                    // String imagen = ""
                    DecimalFormat decimal = new DecimalFormat();
                    decimal.setMaximumFractionDigits(2);
                    contenido  += "Subtotal : "+"              "+"$"+  decimal.format(Float.valueOf(post.getTotal()))+"\n";
                    contenido  += "Iva : "+"                       "+"$"+ decimal.format(Float.valueOf(post.getIva())) +"\n";
                    contenido  += "Total : "+"                   "+"$"+ decimal.format(Float.valueOf(post.getTotalconIva())) +"\n\n";
                //   adapter.add(contenido);


                    //  contenido  += "precio : "+ post.getPrecio()+"\n";
//                    contenido  += "imagen" + post.getImagen()+"\n\n";
                    txtTotales.setText(contenido);

                //  VentasAdapter programAdapter = new VentasAdapter(getApplicationContext(), vectorid, vectorImagen, vectorCedula, vectorcodigo, vectorproducto, vectorcantidad, vectorprecio, vectorSubtotal );
                // lvVentas.setAdapter(programAdapter);
                //   Log.i("Genrado", vectorVideojuego.toString());

                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<Totales> call, Throwable t) {
                // miJson.setText(t.getMessage());

            }
        });

    }
}
