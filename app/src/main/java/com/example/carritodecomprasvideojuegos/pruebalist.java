package com.example.carritodecomprasvideojuegos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
//import android.widget.Toolbar;


import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Login;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.appcompat.widget.Toolbar;

public class pruebalist extends AppCompatActivity {
ListView lvProgram;
Bundle bundle;
List<Login> listaLoginInicio;
Spinner spinern;
int valorCategoria = 1;
    String[] programName = {"C", "C++", "Java", "Android", "HTML5", "CSS3", "JavaScript", "jQuery", "Bootstrap", "PHP",
            "MySQL", "CodeIgniter", "React", "NodeJS", "AngularJS", "PostgreSQL", "Python", "C#", "Wordpress", "GitHub"};
    String[] programDescription = {"C Description", "C++ Description", "Java Description",
            "Android Description", "HTML5 Description",
            "CSS3 Description", "JavaScript Description", "jQuery Description",
            "Bootstrap Description", "PHP Description", "MySQL Description",
            "CodeIgniter Description", "React Description", "NodeJS Description",
            "AngularJS Description", "PostgreSQL Description", "Python Description",
            "C# Description", "Wordpress Description", "GitHub Description"};
    String [] categoria = { "Accion", "Aventura", "Estrategia", "Disparos", "Rol", "Terror", "Survival"   };
    List<String> datos;
    // Define an integer array to hold the image recourse ids
    int[] programImages = {R.drawable.c, R.drawable.cplusplus,
            R.drawable.java, R.drawable.android, R.drawable.html5,
            R.drawable.css3, R.drawable.javascript, R.drawable.jquery,
            R.drawable.bootstrap, R.drawable.php, R.drawable.mysql,
            R.drawable.codeigniter, R.drawable.react, R.drawable.nodejs,
            R.drawable.angularjs, R.drawable.postgresql, R.drawable.python,
            R.drawable.csharp, R.drawable.wordpress, R.drawable.github};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pruebalist);
        Toolbar toolbar = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);
        bundle = getIntent().getExtras();
//       setSupportActionBar(toolbar);
        lvProgram = findViewById(R.id.lvlProgram);
        spinern = findViewById(R.id.sp02);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item , categoria );
        adapter.setDropDownViewResource(R.layout.colorspiner);
        spinern.setAdapter(adapter);
        spinern.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int id = adapterView.getSelectedItemPosition();
                if(id == 0){
                    valorCategoria = 1;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 1){
                    valorCategoria = 2;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 2){
                    valorCategoria = 3;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 3){
                    valorCategoria = 4;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 4){
                    valorCategoria = 5;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 5){
                    valorCategoria = 6;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
                if(id == 6){
                    valorCategoria = 7;
                    nuevoVectorVideoJuegos(valorCategoria);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Specify an adapter and pass context along with all the arrays in constructor
//        com.sandipbhattacharya.listviewwithimage.ProgramAdapter programAdapter = new com.sandipbhattacharya.listviewwithimage.ProgramAdapter(this, programName, programImages, programDescription);
        //ProgramAdapter programAdapter = new ProgramAdapter(this, programName, programImages, programDescription, urls);
//       Vector vector = new Vector();
//        //String vector [] = new String[6];
//        for (int i= 0 ; i< 3; i++){
//          vector.add(String.valueOf(i));
//       }
      nuevoVectorVideoJuegos(valorCategoria);
    ListarLogins();

        // Set the adapter with the ListView
      //  lvProgram.setAdapter(programAdapter);
    }
    public void nuevoVectorVideoJuegos(int valorCategoria){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        Call<List<Videojuego>> call = servicio.getPost(valorCategoria);
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
                List<Videojuego> postVideojuego = response.body();
                int i = 0;

                for (Videojuego post : postVideojuego){
                    vectorVideojuego.add(post.getNombre());
                    //vectorStock.add(post.getCodigo())
                    vectorCodigo.add(post.getCodigo());
                    vectorPrecio.add(post.getPrecio());
                    vectorStock.add(post.getStock());
                    vectorImagen.add(post.getImagen());
                    i++;
                    String contenido = "";
                    // String imagen = ""
                    contenido  += "Codigo : "+ post.getCodigo()+"\n";
                    contenido  += "Nombre : "+ post.getNombre()+"\n";
                 //   adapter.add(contenido);


                    //  contenido  += "precio : "+ post.getPrecio()+"\n";
//                    contenido  += "imagen" + post.getImagen()+"\n\n";
                    //  miJson.append(contenido);
                }
                ProgramAdapter programAdapter = new ProgramAdapter(getApplicationContext(), vectorVideojuego, vectorImagen, vectorStock, vectorCodigo, vectorPrecio);
                lvProgram.setAdapter(programAdapter);
                Log.i("Genrado", vectorVideojuego.toString());

            //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Videojuego>> call, Throwable t) {
               // miJson.setText(t.getMessage());
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK){
           AlertDialog.Builder builder = new AlertDialog.Builder(this);
           builder.setMessage("Desea salir")
                   .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Intent intent = new Intent(Intent.ACTION_MAIN);
                           intent.addCategory(Intent.CATEGORY_HOME);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           startActivity(intent);

                       }
                   })
                   .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           dialogInterface.dismiss();
                       }
                   });
           builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_compras, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();

        if(id==R.id.menu_comprasrealizadas)
        {

            Intent intent = new Intent(pruebalist.this, VistaCarritoCompras.class);
        //    intent.putExtra("UsarioLoginNuevo", listaLoginInicio.get(0).getUsuario());
            startActivity(intent);
            Toast.makeText(this, listaLoginInicio.get(0).getUsuario(), Toast.LENGTH_LONG).show();
        }
        if(id==R.id.menu_compras)
        {
            Toast.makeText(getApplicationContext(), MyApplication.valorInicial, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(pruebalist.this, VistaCompras.class);
           // intent.putExtra("UsarioLoginNuevo", bundle.getString("UsuarioLogin"));
           // Toast.makeText(getApplicationContext(), bundle.getString("UsuarioLogin"), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        if(id==R.id.menuCerrarCesion)
        {
            Intent intent = new Intent(pruebalist.this, MainActivity.class);
            startActivity(intent);
        }
        if(id==R.id.menuUsuario)
        {
            Intent intent = new Intent(pruebalist.this, ModificarPasswordUsuario.class);
            startActivity(intent);
        }
        if(id==R.id.menuUser)
        {
            Intent intent = new Intent(pruebalist.this, editarUsuario.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
    public void ListarLogins(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);

        Call<List<Login>> call = servicio.getLoginiInicioId(MyApplication.valorInicial);

        call.enqueue(new Callback<List<Login>>() {
            @Override
            public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                // List<Videojuego> postVideojuego = response.body();
                //  int i = 0;
                listaLoginInicio = response.body();


                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Login>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });

    }
}