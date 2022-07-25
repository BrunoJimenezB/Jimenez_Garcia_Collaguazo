package com.example.carritodecomprasvideojuegos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {
  private static LayoutInflater inflater = null;

    public Adaptador(String dato, String imagen, Context contexto) {
        this.dato = dato;
        this.imagen = imagen;
        this.contexto = contexto;
        inflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }
    Context contexto;
    String dato = "";
  String imagen = "";
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
       return dato.length();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View vista = inflater.inflate(R.layout.itemvideogames, null);
        TextView codigo = vista.findViewById(R.id.textViewCodigo);
        //TextView nombre = vista.findViewById(R.id.textViewNombre);
       // ImageView image = vista.findViewById(R.id.)
        TextView imagenes = vista.findViewById(R.id.textViewImagen);
        codigo.setText(dato);
        imagenes.setText(imagen);

        return null;
    }
}
