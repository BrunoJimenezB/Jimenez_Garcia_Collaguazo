package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Cliente;
import com.example.carritodecomprasvideojuegos.Modelo.ClienteLogin;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editarUsuario extends AppCompatActivity {
private EditText editTextNombre, editTextApellido, editTextCedula, editTextDireccion, editTextemail;
private Button btnActualizar;
private ImageView imgUserEdidar;
Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        editTextNombre = findViewById(R.id.txtNombreLogin3);
        editTextApellido = findViewById(R.id.txtApellidoLogin3);
        editTextDireccion = findViewById(R.id.txtDireccionLogin3);
        editTextemail = findViewById(R.id.txtEmailLogin3);
        btnActualizar = findViewById(R.id.buttonActualizarClienteLogin);
        imgUserEdidar = findViewById(R.id.imageViewUsreradd3);
        imgUserEdidar.setImageResource(R.drawable.usero);
        ListarClienteId();
btnActualizar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        ClienteLogin clienteId = new ClienteLogin(editTextNombre.getText().toString(), editTextApellido.getText().toString(), editTextDireccion.getText().toString(), editTextemail.getText().toString());
        Call<ClienteLogin> call = servicio.ActualizarCliente(MyApplication.valorInicial, clienteId );
        call.enqueue(new Callback<ClienteLogin>() {
            @Override
            public void onResponse(Call<ClienteLogin> call, Response<ClienteLogin> response) {
                Toast.makeText(editarUsuario.this, "Usuario Actualizado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), pruebalist.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ClienteLogin> call, Throwable t) {

            }
        });
    }
});
    }
public void ListarClienteId(){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://res-movil.herokuapp.com/api/games/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
    Call<Cliente> call = servicio.listaClientesId(MyApplication.valorInicial);
    call.enqueue(new Callback<Cliente>() {
        @Override
        public void onResponse(Call<Cliente> call, Response<Cliente> response) {
            cliente = response.body();

            editTextNombre.setText(cliente.getNombre().toString());
            editTextApellido.setText(cliente.getApellido().toString());
            editTextDireccion.setText(cliente.getDireccion().toString());
            editTextemail.setText(cliente.getCorreo());
        }

        @Override
        public void onFailure(Call<Cliente> call, Throwable t) {
            Toast.makeText(editarUsuario.this, "Fallo al obtener clientes", Toast.LENGTH_SHORT).show();
        }
    });
}

}