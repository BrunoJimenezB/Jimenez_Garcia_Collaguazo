package com.example.carritodecomprasvideojuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carritodecomprasvideojuegos.Interfaz.ServiciosApi;
import com.example.carritodecomprasvideojuegos.Modelo.Cliente;
import com.example.carritodecomprasvideojuegos.Modelo.Exportar;
import com.example.carritodecomprasvideojuegos.Modelo.Login;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearUsuario extends AppCompatActivity {
private EditText txtnombre, txtApellido, txtCorreo, txtDireccion, txtCedula,  txtEmail;
TextInputEditText txtPassword;
private Button btnCrearUsuario;
List<Cliente> listaClientes;
Login login;
Cliente cliente;
ImageView imagenUserAdd;
TextView textViewMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        txtnombre = findViewById(R.id.txtNombreLogin);
        txtApellido = findViewById(R.id.txtApellidoLogin);
        txtDireccion = findViewById(R.id.txtDireccionLogin);
        txtCedula = findViewById(R.id.txtCedulaLogin);
        txtPassword = findViewById(R.id.txtPasswordLogin);
        btnCrearUsuario = findViewById(R.id.buttonReigstroLogin);
        txtEmail = findViewById(R.id.txtEmailLogin);
        imagenUserAdd = findViewById(R.id.imageViewUsreradd);
        imagenUserAdd.setImageResource(R.drawable.useradd);
        textViewMensaje = findViewById(R.id.textViewMensajeContra);
        textViewMensaje.setVisibility(View.GONE); //deshabilitar el mensaje
        ListaClientes();
        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valor=false;
                String cedula = txtCedula.getText().toString();
              boolean cedulaV=  validadorDeCedula(cedula);
              if (cedulaV == true ) {
                  for (Cliente listadoClientes: listaClientes){
                      if(listadoClientes.getCedula().equals(cedula)){
                          Toast.makeText(CrearUsuario.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                          valor=true;
                      }
                  }
                  if(valor.equals(true)){
                      Toast.makeText(CrearUsuario.this, "Usuario ya existe", Toast.LENGTH_SHORT).show();
                      valor = false;
                  }else {
                      //  String token = "eJIDFAOKisFDSFJASLFS==28kDAVz";
                      MyApplication.valorInicial = txtCedula.getText().toString();
                      createCliente(txtCedula.getText().toString(), txtnombre.getText().toString(), txtApellido.getText().toString(), txtDireccion.getText().toString(), txtEmail.getText().toString());
                      CreateLogin(txtCedula.getText().toString(), txtPassword.getText().toString(), txtEmail.getText().toString());
                      //         Toast.makeText(getApplicationContext(), listaClientes.get(1).getNombre().toString(), Toast.LENGTH_SHORT).show();
                  }                                                                     
              } else{
                  Toast.makeText(getApplicationContext(), "Cedula Incorrecta", Toast.LENGTH_SHORT).show();
              }

                }
        });
    }
    public void CreateLogin(String cedula, String password, String correo){
        if (ContraValidarCaracteres.PASSWORD_PATTERN.matcher(password).matches()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://res-movil.herokuapp.com/api/games/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ServiciosApi servicio =  retrofit.create(ServiciosApi.class);

            login  = new Login(cedula, password, correo);
            Call<Login> call = servicio.createPostLogin(login);

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
                    Toast.makeText(CrearUsuario.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();


                    //    vistaVideojuegos.setAdapter(adapter);
                    // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    // miJson.setText(t.getMessage());
                }
            });
            //Toast.makeText(getApplicationContext(), "Contra validad", Toast.LENGTH_SHORT).show();
            //  System.out.print("The Password " + password + " is valid");
        } else {
            Toast.makeText(getApplicationContext(), "Contra Denegado Ingrese caracteres", Toast.LENGTH_SHORT).show();
            textViewMensaje.setVisibility(View.VISIBLE);
            //  System.out.print("The Password " + password + " isn't valid");
        }


    }
    public void ListaClientes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
//  /      login  = new Login(cedula, password, token);
        Call<List<Cliente>> call = servicio.getPostClientes();
//        Vector vectorVideojuego = new Vector();
//        Vector vectorStock = new Vector();
//        Vector vectorCodigo = new Vector();
//        Vector vectorPrecio = new Vector<>();
//        Vector vectorImagen  = new Vector();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                // List<Videojuego> postVideojuego = response.body();
                //  int i = 0;
                listaClientes = response.body();


                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });
    }
    public void createCliente(String cedula, String nombre, String apellido, String direccion, String email){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://res-movil.herokuapp.com/api/games/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiciosApi servicio =  retrofit.create(ServiciosApi.class);
        cliente  = new Cliente(cedula, nombre, apellido, direccion, email);
        Call<Cliente> call = servicio.createCliente(cliente);

        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(!response.isSuccessful()){
                    //  miJson.setText("Codigo : "+ response.code());
                    return;
                }
                // List<Videojuego> postVideojuego = response.body();
                //  int i = 0;
//                Toast.makeText(CrearUsuario.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//                finish();


                //    vistaVideojuegos.setAdapter(adapter);
                // vistaVideojuegos.setAdapter(new Adaptador( postVideojuego.get(0).getNombre().toString(), postVideojuego.get(0).getCodigo().toString(), Inicio.this));
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                // miJson.setText(t.getMessage());
            }
        });

    }
    public boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;

        try {

            if (cedula.length() == 10) // ConstantesApp.LongitudCedula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
// Coeficientes de validación cédula
// El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    }
                    else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;
                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }
        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            Toast.makeText(this, "Cedula Ingresada Incorrecta", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Cedula Correcta", Toast.LENGTH_SHORT).show();
        }
        return cedulaCorrecta;
    }

}