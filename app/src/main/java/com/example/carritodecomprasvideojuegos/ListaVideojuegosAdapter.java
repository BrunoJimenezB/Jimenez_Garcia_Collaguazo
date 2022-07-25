package com.example.carritodecomprasvideojuegos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.ArrayList;

public class ListaVideojuegosAdapter extends RecyclerView.Adapter<ListaVideojuegosAdapter.ViewHolder> {
    private ArrayList<Videojuego> dataset;
    public ListaVideojuegosAdapter(){
        dataset = new ArrayList<>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemvideogames, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Videojuego videojuego = dataset.get(position);
     holder.nombreTextView.setText(videojuego.getNombre());
    }



    @Override
    public int getItemCount() {
        return dataset.size()   ;
    }

    public void adicionarListaVideojuego(ArrayList<Videojuego> listaVideojuego) {
        dataset.addAll(listaVideojuego);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView nombreTextView;
        private TextView precioTextView;
        private TextView stockTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // imagen = itemView.findViewById(R.id.imageViewVideo);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            precioTextView =  itemView.findViewById(R.id.textViewPrecio);
            stockTextView =  itemView.findViewById(R.id.textViewStock);
        }
    }
}
