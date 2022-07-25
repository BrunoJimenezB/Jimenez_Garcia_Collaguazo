package com.example.carritodecomprasvideojuegos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Exportar;
import com.example.carritodecomprasvideojuegos.Modelo.Factura;
import com.example.carritodecomprasvideojuegos.Modelo.FacturaDetalle;
import com.example.carritodecomprasvideojuegos.Modelo.FacturaDetallev2;
import com.example.carritodecomprasvideojuegos.Modelo.Producto;
import com.example.carritodecomprasvideojuegos.Modelo.Totales;
import com.example.carritodecomprasvideojuegos.Modelo.Venta;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;
import com.example.carritodecomprasvideojuegos.Modelo.facturaCabecera;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VistaCarritoCompras extends AppCompatActivity {
    ListView lvVentas;
    TextView textTotales;
    Button btnCompras;
    List<Venta> postVideojuego;
    List<Totales> postTotales;
    FacturaDetalle facturaDetalle;
    FacturaDetalle factura[];
    List<FacturaDetalle> guardarFactura;
    List<Factura> postFactura;
    List<Videojuego> postVideojuegoTotales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_carrito_compras);
        Toolbar toolbar = findViewById(R.id.toolbarMenu3);
        setSupportActionBar(toolbar);
        lvVentas = findViewById(R.id.listViewCarritoCompras);
        textTotales = findViewById(R.id.textViewTotales);
        btnCompras = findViewById(R.id.buttonCompra);
        nuevoVectorVideoJuegos();
        calculoTotales();
        ObtenerUltimaFactura();
        ObtenerProductosTotales();
       // Log.i("texto",postVideojuegoTotales.get(0).getNombre().toString());
        btnCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             if(postVideojuego.size()==0) {
                 Toast.makeText(VistaCarritoCompras.this, "No hay productos a guardar", Toast.LENGTH_SHORT).show();
             }else {
                 //  BorrarDatosCarrito();


//Valores despues
                 CompraProductos();
                 BorrarDatosCarrito();
               //  finish();
                 //  ActualizarProductos();
                 //  Toast.makeText(getApplicationContext(), postVideojuego.get(1).getProductoexportar().toString()  , Toast.LENGTH_SHORT).show();
             }
            }
        });
    }
    public void nuevoVectorVideoJuegos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        Call<List<Venta>> call = servicio.getPostVenta();
        Vector vectorid = new Vector();
        Vector vectorCedula = new Vector();
        Vector vectorcodigo = new Vector();
        Vector vectorproducto= new Vector();
        Vector vectorcantidad = new Vector();
        Vector vectorSubtotal= new Vector<>();
        Vector vectorImagen  = new Vector();
        Vector vectorprecio = new Vector();
        call.enqueue(new Callback<List<Venta>>() {
            @Override
            public void onResponse(Call<List<Venta>> call, Response<List<Venta>> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                 postVideojuego = response.body();
                int i = 0;

                for (Venta post : postVideojuego){
                    vectorid.add(post.getId());
                    vectorCedula.add(post.getCedulaexportar());
                    vectorSubtotal.add(post.getSubtotal());
                    vectorcodigo.add(post.getCodigoexportar());
                    //vectorStock.add(post.getCodigo())
                    vectorproducto.add(post.getProductoexportar());
                    vectorprecio.add(post.getPrecioexportar());
                    vectorcantidad.add(post.getCantidadexportar());
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
                VentasAdapter programAdapter = new VentasAdapter(getApplicationContext(), vectorid, vectorImagen, vectorCedula, vectorcodigo, vectorproducto, vectorcantidad, vectorprecio, vectorSubtotal );
                lvVentas.setAdapter(programAdapter);
             //   Log.i("Genrado", vectorVideojuego.toString());

                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Venta>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });

    }
    public void calculoTotales(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        Call<List<Totales>> call = servicio.getPostTotales();
//        Vector vectorid = new Vector();
//        Vector vectorCedula = new Vector();
//        Vector vectorcodigo = new Vector();
//        Vector vectorproducto= new Vector();
//        Vector vectorcantidad = new Vector();
//        Vector vectorSubtotal= new Vector<>();
//        Vector vectorImagen  = new Vector();
//        Vector vectorprecio = new Vector();
        call.enqueue(new Callback<List<Totales>>() {
            @Override
            public void onResponse(Call<List<Totales>> call, Response<List<Totales>> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
             postTotales = response.body();
                int i = 0;

                for (Totales post : postTotales){
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
                      textTotales.append(contenido);
                }
              //  VentasAdapter programAdapter = new VentasAdapter(getApplicationContext(), vectorid, vectorImagen, vectorCedula, vectorcodigo, vectorproducto, vectorcantidad, vectorprecio, vectorSubtotal );
               // lvVentas.setAdapter(programAdapter);
                //   Log.i("Genrado", vectorVideojuego.toString());

                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Totales>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });

    }
    public void BorrarDatosCarrito(){
        //Inicio metodo
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
     //   int valorId =  Integer.valueOf(bundle.get("idVenta").toString());
        Call<Exportar> call = servicio.borratCarrito();
        call.enqueue(new Callback<Exportar>() {
            @Override
            public void onResponse(Call<Exportar> call, Response<Exportar> response) {
                if(!response.isSuccessful()){
                    return;
                }
                Exportar postVenta = response.body();

                Toast.makeText(getApplicationContext(), "Compra Exitosa", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), VistaCarritoCompras.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Exportar> call, Throwable t) {
                //  miJson.setText(t.getMessage());
            }
        });
    }
    public void CompraProductos(){
        String contenido = "";
        int idFactura = postFactura.get(0).getId_factura() +1;
       guardarFactura = new ArrayList<>();
//        for (Venta post : postVideojuego){
//         //  contenido = contenido + post.getProductoexportar();
//            //Log.i("Msg",  postVideojuego.get);
//            facturaDetalle = new FacturaDetalle(idFactura, post.getCodigoexportar(), post.getCantidadexportar());
//            guardarFactura.add(facturaDetalle);
//        }
        //Guardamos en la base

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);

        facturaCabecera exportar  = new facturaCabecera(postTotales.get(0).getTotal(), postTotales.get(0).getIva(), postTotales.get(0).getTotalconIva() );

      //  Bundle bundle = getIntent().getExtras();
       // String cedulaLogin = bundle.getString("UsuarioLogin");
        Call<facturaCabecera> call = servicio.FacturaTransaccion(postVideojuego.get(0).getCedulaexportar(), exportar);
        Toast.makeText(this, String.valueOf(postFactura.get(0).getId_factura()+1), Toast.LENGTH_SHORT).show();
//        Vector vectorVideojuego = new Vector();
//        Vector vectorStock = new Vector();
//        Vector vectorCodigo = new Vector();
//        Vector vectorPrecio = new Vector<>();
//        Vector vectorImagen  = new Vector();
        call.enqueue(new Callback<facturaCabecera>() {
            @Override
            public void onResponse(Call<facturaCabecera> call, Response<facturaCabecera> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                // List<Videojuego> postVideojuego = response.body();
                //  int i = 0;
                Toast.makeText(VistaCarritoCompras.this, "Compra realizada correctamente", Toast.LENGTH_SHORT).show();
                agregarDetalle();
                ActualizarProductos();
                //  ActualizarProductos();


                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<facturaCabecera> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });
       ;


       //Log.i("Msg", String.valueOf(postFactura.get(0).getId_factura()));
    }
    public void agregarDetalle(){
       // Toast.makeText(this, postFactura.get(0), Toast.LENGTH_SHORT).show();
        int facturaidl= Integer.valueOf(postFactura.get(0).getId_factura())+1;
             Toast.makeText(getApplicationContext(), postVideojuego.get(0).getCodigoexportar(), Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
      //  FacturaDetallev2 facturaDetalle= new FacturaDetallev2(postVideojuego.get(0).getCantidadexportar(),Float.valueOf(postVideojuego.get(0).getTotal()), facturaidl, Integer.valueOf(postVideojuego.get(0).getCodigoexportar().toString()) );
     //   Toast.makeText(this, String.valueOf(postVideojuego.get(0).getCantidadexportar() * postVideojuego.get(0).getPrecioexportar()), Toast.LENGTH_SHORT).show();
        for( Venta pos : postVideojuego) {
            FacturaDetallev2 facturaDetalle= new FacturaDetallev2(pos.getCantidadexportar(), pos.getCantidadexportar()*pos.getPrecioexportar(), facturaidl, Integer.valueOf(pos.getCodigoexportar()));
            Call<FacturaDetallev2> call = servicio.createDetalle(facturaDetalle);
            call.enqueue(new Callback<FacturaDetallev2>() {
                @Override
                public void onResponse(Call<FacturaDetallev2> call, Response<FacturaDetallev2> response) {
                    Toast.makeText(VistaCarritoCompras.this, "Detalle Agregado", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<FacturaDetallev2> call, Throwable t) {
                    Toast.makeText(VistaCarritoCompras.this, "Detalle error", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void ObtenerUltimaFactura(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        Call<List<Factura>> call = servicio.ObtnerUltimaFactura();
//        Vector vectorVideojuego = new Vector();
//        Vector vectorStock = new Vector();
//        Vector vectorCodigo = new Vector();
//        Vector vectorPrecio = new Vector<>();
//        Vector vectorImagen  = new Vector();
        call.enqueue(new Callback<List<Factura>>() {
            @Override
            public void onResponse(Call<List<Factura>> call, Response<List<Factura>> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                 postFactura = response.body();
//                int i = 0;

//                for (Factura post : postVideojuego){
////                    vectorVideojuego.add(post.getNombre());
////                    //vectorStock.add(post.getCodigo())
////                    vectorCodigo.add(post.getCodigo());
////                    vectorPrecio.add(post.getPrecio());
////                    vectorStock.add(post.getStock());
////                    vectorImagen.add(post.getImagen());
////                    i++;
//                    String contenido = "";
//                    // String imagen = ""
//                    contenido  += "Codigo : "+ post.getCodigo()+"\n";
//                    contenido  += "Nombre : "+ post.getNombre()+"\n";
//                    //   adapter.add(contenido);
//
//
//                    //  contenido  += "precio : "+ post.getPrecio()+"\n";
////                    contenido  += "imagen" + post.getImagen()+"\n\n";
//                    //  miJson.append(contenido);
//                }
//                ProgramAdapter programAdapter = new ProgramAdapter(getApplicationContext(), vectorVideojuego, vectorImagen, vectorStock, vectorCodigo, vectorPrecio);
//                lvProgram.setAdapter(programAdapter);
//                Log.i("Genrado", vectorVideojuego.toString());

                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Factura>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });
    }
    public void ObtenerProductosTotales(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        Call<List<Videojuego>> call = servicio.getPostProductosTotales();
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
                postVideojuegoTotales = response.body();

                int i = 0;



                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Videojuego>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });
    }
    public void ActualizarProductos(){
        int stockRestado=0;
        String nuevo="";
      //  Toast.makeText(this, String.valueOf(postVideojuego.get(0).getCantidadexportar()), Toast.LENGTH_SHORT).show();
        Producto videoJuegoActulizado ;
        for (Videojuego totalesVideojuego : postVideojuegoTotales){
            for (Venta totalesVenta : postVideojuego){

               if(totalesVideojuego.getCodigo().toString().contains(totalesVenta.getCodigoexportar().toString())){
                  // stockRestado=1;
                   nuevo = totalesVideojuego.getCodigo().toString();
                    stockRestado = Integer.valueOf(totalesVideojuego.getStock().toString()) - totalesVenta.getCantidadexportar();

                    Toast.makeText(getApplicationContext(), String.valueOf(stockRestado), Toast.LENGTH_SHORT).show();
                   //totalesVideojuego.setStock(String.valueOf(stockRestado));
                   videoJuegoActulizado= new Producto( stockRestado);
                  // Toast.makeText(getApplicationContext(), totalesVideojuego.getStock(), Toast.LENGTH_SHORT).show();
                   Retrofit retrofit = new Retrofit.Builder()
                           .baseUrl("https://res-movil.herokuapp.com/api/games/")
                           .addConverterFactory(GsonConverterFactory.create())
                           .build();
                   ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
                   Call<Producto> call = servicio.updateVideojuegoStock(Integer.valueOf(totalesVideojuego.getCodigo().toString()), videoJuegoActulizado);
//                   Vector vectorVideojuego = new Vector();
//                   Vector vectorStock = new Vector();
//                   Vector vectorCodigo = new Vector();
//                   Vector vectorPrecio = new Vector<>();
//                   Vector vectorImagen  = new Vector();
                   call.enqueue(new Callback<Producto>() {
                       @Override
                       public void onResponse(Call<Producto> call, Response<Producto> response) {
                           if(!response.isSuccessful()){
                               //  miJson.setText("Codigo : "+ response.code());
                               return;
                           }
                          // videoJuegoActulizado = response.body();
                     Toast.makeText(getApplicationContext(), "Stock Actualizado", Toast.LENGTH_SHORT).show();
                           int i = 0;



                           //    vistaVideojuegos.setAdapter(adapter);
                           // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
                       }

                       @Override
                       public void onFailure(Call<Producto> call, Throwable t) {
                           // miJson.setText(t.getMessage());
                       }
                   });
               }
             }
        }
       // Toast.makeText(getApplicationContext(), String.valueOf(stockRestado), Toast.LENGTH_SHORT).show();
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
           // finish();
            //Toast.makeText(this, "Presiono en compras: ", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}