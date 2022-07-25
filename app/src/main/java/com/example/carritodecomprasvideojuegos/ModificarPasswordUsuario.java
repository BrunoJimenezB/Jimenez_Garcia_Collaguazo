package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Login;
import com.example.carritodecomprasvideojuegos.Modelo.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarPasswordUsuario extends AppCompatActivity {
EditText contraActualtxt;
EditText contraNuevatxt;
Button btnCambiarContra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_password_usuario);
        contraActualtxt = findViewById(R.id.txtContraActual);
        contraNuevatxt = findViewById(R.id.txtContraNueva);
        btnCambiarContra = findViewById(R.id.buttonCambiarContra);
        btnCambiarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarContra();
              //  ValidaContraCaracteres();
            }
        });
    }
    public void CambiarContra(){
        if(contraActualtxt.getText().toString().contains(MyApplication.valorContra)){
            if (ContraValidarCaracteres.PASSWORD_PATTERN.matcher(contraNuevatxt.getText().toString()).matches()) {
             //   Toast.makeText(getApplicationContext(), "Contra validad", Toast.LENGTH_SHORT).show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://res-movil.herokuapp.com/api/games/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
                Usuario user = new Usuario(contraNuevatxt.getText().toString());
                Call<Usuario> call = servicio.updateContraUsuario(MyApplication.valorInicial, user);
                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Toast.makeText(getApplicationContext(), "Contraseña Cambiada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        //   finish();
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "No se pudo Cambiar la Contraseña ", Toast.LENGTH_SHORT).show();
                    }
                });
                //  System.out.print("The Password " + password + " is valid");
            } else {
                Toast.makeText(getApplicationContext(), "Contra Denegado Ingrese caracteres", Toast.LENGTH_SHORT).show();
                //  System.out.print("The Password " + password + " isn't valid");
            }
             }else{
            Toast.makeText(this, "Incorrecta Contraseña Actual", Toast.LENGTH_SHORT).show();
        }
    }
    public void ValidaContraCaracteres()

    {
        if (ContraValidarCaracteres.PASSWORD_PATTERN.matcher(contraNuevatxt.getText().toString()).matches()) {
            Toast.makeText(getApplicationContext(), "Contra validad", Toast.LENGTH_SHORT).show();
          //  System.out.print("The Password " + password + " is valid");
        } else {
            Toast.makeText(getApplicationContext(), "Contra Denegado Ingrese caracteres", Toast.LENGTH_SHORT).show();
          //  System.out.print("The Password " + password + " isn't valid");
        }
    }


}