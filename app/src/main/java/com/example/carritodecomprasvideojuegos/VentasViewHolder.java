package com.example.carritodecomprasvideojuegos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VentasViewHolder
{
    ImageView itemImage;
    //TextView nombre;
    TextView codigo;
    TextView producto;
    TextView cantidad;
    TextView subtotal;
    TextView precio;
    // Get the handles by calling findViewById() on View object inside the constructor
    VentasViewHolder(View v)
    {
        itemImage = v.findViewById(R.id.imageViewCarritoVentas);
     //   nombre = v.findViewById(R.id.textViewProductoVenta);
        codigo = v.findViewById(R.id.textViewCodigoVenta);
        producto = v.findViewById(R.id.textViewProductoVenta);
        precio = v.findViewById(R.id.textViewPrecioVenta);
        cantidad = v.findViewById(R.id.textViewCantidadVenta);
        subtotal = v.findViewById(R.id.textViewSubtotalVenta);

    }
}
