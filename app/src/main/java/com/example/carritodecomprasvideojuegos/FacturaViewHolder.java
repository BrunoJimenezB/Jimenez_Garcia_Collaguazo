package com.example.carritodecomprasvideojuegos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FacturaViewHolder {

    ImageView itemImage;
    //TextView nombre;
    TextView txtidFactura;
    TextView txtcedula;
    TextView txtFecha;


    // Get the handles by calling findViewById() on View object inside the constructor
    FacturaViewHolder(View v)
    {
        itemImage = v.findViewById(R.id.imageViewComprasF);
        //   nombre = v.findViewById(R.id.textViewProductoVenta);
        txtidFactura = v.findViewById(R.id.textViewFacturaId);
        txtcedula = v.findViewById(R.id.textViewCedulaFactura);
        txtFecha = v.findViewById(R.id.textViewFechaFactura);


    }
}
