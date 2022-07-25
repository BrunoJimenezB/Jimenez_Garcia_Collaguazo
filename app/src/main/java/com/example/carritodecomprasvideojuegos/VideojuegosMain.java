package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideojuegosMain extends AppCompatActivity {
 RecyclerView recyclerView;
 ProgressBar progressBar;
 LinearLayoutManager  layoutManager;
 VideojuegoAdapter adapter;
 List<Videojuego> postsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videojuegos_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar2);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new VideojuegoAdapter(postsList);
        recyclerView.setAdapter(adapter);

        getDats();
    }

    private void getDats() {
        RetrofitClient.getRetrofitClient().getPosts().enqueue(new Callback<List<Videojuego>>() {
            @Override
            public void onResponse(Call<List<Videojuego>> call, Response<List<Videojuego>> response) {
                if (response.isSuccessful() && response.body()!= null){
//                    postsList.addAll(response.body());
                    postsList = response.body();

                  adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Videojuego>> call, Throwable t) {
                Toast.makeText(VideojuegosMain.this,"Error: "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}