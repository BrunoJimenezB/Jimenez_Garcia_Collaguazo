package com.example.carritodecomprasvideojuegos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProgramViewHolder {
    // Declare the object references for our views
    ImageView itemImage;
    TextView programTitle;
    TextView programDescription;
    TextView codigo;
    TextView precio;
    // Get the handles by calling findViewById() on View object inside the constructor
    ProgramViewHolder(View v)
    {
        itemImage = v.findViewById(R.id.imageView);
        programTitle = v.findViewById(R.id.textviewNombreP);
        programDescription = v.findViewById(R.id.textviewNombreP2);
        codigo = v.findViewById(R.id.textViewCodig);
        precio = v.findViewById(R.id.textViewPrecioVideojuego);

    }
}
