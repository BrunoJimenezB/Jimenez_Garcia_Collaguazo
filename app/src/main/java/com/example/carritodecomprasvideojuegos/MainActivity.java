package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Cliente;
import com.example.carritodecomprasvideojuegos.Modelo.Login;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
private Button botonInicio;
private Button botonCrearUsuario;
List<Login> listalogin;
Login loginInicio;
TextView txtUsuario;
TextInputEditText txtPassword;
ImageView imageLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);
        botonInicio = findViewById(R.id.button);
        botonCrearUsuario = findViewById(R.id.buttonRegistrar);
        txtPassword = findViewById(R.id.txtPasswordMain);
        txtUsuario = findViewById(R.id.txtUsuarioMain);
        imageLogin = findViewById(R.id.imageViewLogin);
        imageLogin.setImageResource(R.drawable.userorigin);
       // imageLogin.

        ListarLogins();
botonInicio.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      //  Toast.makeText(MainActivity.this, listalogin.get(0).getPassword(), Toast.LENGTH_SHORT).show();
        ValidadUsuario();

        //finish();
//      Intent intent = new Intent(MainActivity.this, pruebalist.class);
//      startActivity(intent);
    //    Toast.makeText(MainActivity.this, listalogin.get(1).getUsuario().toString(), Toast.LENGTH_SHORT).show();
    }
});
botonCrearUsuario.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), CrearUsuario.class);
        startActivity(intent);

    }
});
    }
    public void ListarLogins(){

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://res-movil.herokuapp.com/api/games/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ServiciosApi servicio =  retrofit.create(ServiciosApi.class);

            Call<List<Login>> call = servicio.getPostLogins();

            call.enqueue(new Callback<List<Login>>() {
                @Override
                public void onResponse(Call<List<Login>> call, Response<List<Login>> response) {
                    if(!response.isSuccessful()){
                        //  miJson.setText("Codigo : "+ response.code());
                        return;
                    }
                    // List<Videojuego> postVideojuego = response.body();
                    //  int i = 0;
                    listalogin = response.body();


                    //    vistaVideojuegos.setAdapter(adapter);
                    // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
                }

                @Override
                public void onFailure(Call<List<Login>> call, Throwable t) {
                    // miJson.setText(t.getMessage());
                }
            });

    }
    public void ValidadUsuario(){
        String usuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();
        String NombreUsuario = "";

        Boolean valor = false;
        for (Login list : listalogin){
            if(list.getUsuario().equals(usuario) && list.getPassword().equals(password)) {
                valor = true;
                NombreUsuario = list.getUsuario();
            }
        }
        if(valor.equals(true)){
            crearLoginInicio();
            Toast.makeText(this, "Bienvenido Usuario: "+ txtUsuario.getText().toString(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, pruebalist.class);
            intent.putExtra("UsuarioLogin", txtUsuario.getText().toString());
      startActivity(intent);


        }else{
            Toast.makeText(this, "Usuario o contraseña Incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
    public void crearLoginInicio(){
        String usuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();

        MyApplication.valorInicial = usuario;
        MyApplication.valorContra = password;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        loginInicio  = new Login(usuario, password);
        Call<Login> call = servicio.createLoginInicio(loginInicio);
//        Vector vectorVideojuego = new Vector();
//        Vector vectorStock = new Vector();
//        Vector vectorCodigo = new Vector();
//        Vector vectorPrecio = new Vector<>();
//        Vector vectorImagen  = new Vector();
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                // List<Videojuego> postVideojuego = response.body();
                //  int i = 0;
                Toast.makeText(MainActivity.this, "Login Inicio agregado", Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
              //  startActivity(intent);
               // finish();


                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });
    }

}