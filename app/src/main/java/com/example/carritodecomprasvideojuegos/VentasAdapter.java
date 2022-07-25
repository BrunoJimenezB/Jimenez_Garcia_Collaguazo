package com.example.carritodecomprasvideojuegos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Totales;
import com.example.carritodecomprasvideojuegos.Modelo.Venta;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VentasAdapter extends ArrayAdapter<String> {
    Context context;
    Vector images;
    //String[] programName;
    Vector id;
    Vector cedula;
    Vector codigo;
    Vector precio;
    Vector nombre;
    Vector cantidad;
    Vector Subtotal;
    //String[] urls;

    public VentasAdapter(Context context, Vector id, Vector images, Vector cedula, Vector codigo, Vector nombre, Vector cantidad,  Vector precio, Vector subtotal  ) {
        super(context, R.layout.single_item, R.id.textviewNombreP, id);
        this.context = context;
        this.images = images;
        this.id = id;
        this.cedula = cedula;
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.Subtotal = subtotal;
    }

    /*
    Additional code
    public ProgramAdapter(Context context, String[] programName, int[] images, String[] programDescription, String[] urls) {
        super(context, R.layout.single_item, R.id.textView1, programName);
        this.context = context;
        this.images = images;
        this.programName = programName;
        this.programDescription = programDescription;
        this.urls = urls;
    }
     */

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // The parameter convertView is null when your app is creating a new item for the first time. It's not null when
        // recycling.
        // Assign the convertView in a View object
        View singleItem = convertView;
        // Find a View from the entire View hierarchy by calling findViewById() is a fairly expensive task.
        // So, you'll create a separate class to reduce the number of calls to it.
        // First, create a reference of ProgramViewHolder and assign it to null.
        VentasViewHolder holder = null;
        // Since layout inflation is a very expensive task, you'll inflate only when creating a new item in the ListView. The first
        // time you're creating a new item, convertView will be null. So, the idea is when creating an item for the first time,
        // we should perform the inflation and initialize the ViewHolder.
        if(singleItem == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem = layoutInflater.inflate(R.layout.listaventascarrito, parent, false);
            // Pass the singleItem to the constructor of ProgramViewHolder. This singleItem object contains a LinearLayout
            // as the root element for single_item.xml file that contains other Views as well for the ListView.
            holder = new VentasViewHolder(singleItem);
            // When you create an object of ProgramViewHolder, you're actually calling findViewById() method inside the constructor.
            // By creating ProgramViewHolder only when making new items, you call findViewById() only when making new rows.
            // At this point all the three Views have been initialized. Now you need to store the holder so that you don't need to
            // create it again while recycling and you can do this by calling setTag() method on singleItem and passing the holder as a parameter.
            singleItem.setTag(holder);
        }
        // If singleItem is not null then we'll be recycling
        else{
            // Get the stored holder object
            holder = (VentasViewHolder) singleItem.getTag();
        }
        // Set the values for your views in your item by accessing them through the holder
        //  holder.itemImage.setImageResource(images.get(position).toString());
        Picasso.get()
                .load(images.get(position).toString())
                .resize(100, 150)
                .into(holder.itemImage);
        holder.producto.setText(nombre.get(position).toString());
        holder.codigo.setText(codigo.get(position).toString());
        holder.precio.setText(precio.get(position).toString());
        holder.cantidad.setText(cantidad.get(position).toString());
        holder.subtotal.setText(Subtotal.get(position).toString());
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // intent.putExtra("nombreProducto", programName.get(position).toString());
              //  intent.putExtra("Stock", .get(position).toString());
             //   intent.putExtra("PrecioProducto", precio.get(position).toString());
               // intent.putExtra("imageProducto", images.get(position).toString());
               // intent.putExtra("codig", codigo.get(position).toString());


                //Inicio metodo
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://res-movil.herokuapp.com/api/games/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
                int valorId = (int) id.get(position);
                Call<Venta> call = servicio.getPostVentaOne(valorId);
                call.enqueue(new Callback<Venta>() {
                    @Override
                    public void onResponse(Call<Venta> call, Response<Venta> response) {
                        if(!response.isSuccessful()){
                        return;
                        }
                        Venta postVenta = response.body();

               //         Toast.makeText(getContext(), "You clicked:"+ images.get(position).toString(), Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(context, ActualizarProducto.class);
                        intent.putExtra("NombreVenta", postVenta.getProductoexportar().toString());
                        intent.putExtra("StockVenta", postVenta.getStock().toString());
                        intent.putExtra("NombreVideojuego", postVenta.getProductoexportar().toString());
                        intent.putExtra("PrecioVenta", String.valueOf(postVenta.getPrecioexportar()));
                        intent.putExtra("CantidadVenta", String.valueOf(postVenta.getCantidadexportar()));
                        intent.putExtra("ImagenVenta", images.get(position).toString());
                        intent.putExtra("idVenta", id.get(position).toString());
                        intent.putExtra("codigoVenta", codigo.get(position).toString());
                       context.startActivity(intent);
                     //   ((Activity)context).finish();

                    }

                    @Override
                    public void onFailure(Call<Venta> call, Throwable t) {
                      //  miJson.setText(t.getMessage());
                    }
                });


            }
        });
        return singleItem;
    }
}
