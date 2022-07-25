package com.example.carritodecomprasvideojuegos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.List;

public class VideojuegoAdapter  extends RecyclerView.Adapter<VideojuegoAdapter.ViewHolder> {
    public VideojuegoAdapter(List<Videojuego> postList) {
        this.postList = postList;
    }

    private List<Videojuego> postList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemvideogames, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombre.setText(postList.get(position).getNombre());
        holder.codigo.setText(postList.get(position).getCodigo());
//        holder.precio.setText( String.valueOf(postList.get(position).getPrecio()));
//        holder.stock.setText(String.valueOf(postList.get(position).getStock()));
        holder.imagen.setText(postList.get(position).getImagen());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder {
        TextView  nombre;
        TextView  codigo;

        TextView  imagen;
      //  ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.textViewNombre);
            codigo = itemView.findViewById(R.id.textViewCodigo);

            imagen = itemView.findViewById(R.id.textViewImagen);

        }
    }
}
