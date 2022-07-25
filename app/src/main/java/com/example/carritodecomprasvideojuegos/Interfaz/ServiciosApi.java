package com.example.carritodecomprasvideojuegos.Interfaz;

import com.example.carritodecomprasvideojuegos.Modelo.Cliente;
import com.example.carritodecomprasvideojuegos.Modelo.ClienteLogin;
import com.example.carritodecomprasvideojuegos.Modelo.Exportar;
import com.example.carritodecomprasvideojuegos.Modelo.ExportarVenta;
import com.example.carritodecomprasvideojuegos.Modelo.Factura;
import com.example.carritodecomprasvideojuegos.Modelo.FacturaDetalle;
import com.example.carritodecomprasvideojuegos.Modelo.FacturaDetallev2;
import com.example.carritodecomprasvideojuegos.Modelo.FacturaVenta;
import com.example.carritodecomprasvideojuegos.Modelo.Login;
import com.example.carritodecomprasvideojuegos.Modelo.Producto;
import com.example.carritodecomprasvideojuegos.Modelo.Totales;
import com.example.carritodecomprasvideojuegos.Modelo.Usuario;
import com.example.carritodecomprasvideojuegos.Modelo.Venta;
import com.example.carritodecomprasvideojuegos.Modelo.VentaFactura;
import com.example.carritodecomprasvideojuegos.Modelo.Videojuego;
import com.example.carritodecomprasvideojuegos.Modelo.VideojuegosRespuesta;
import com.example.carritodecomprasvideojuegos.Modelo.facturaCabecera;

import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiciosApi {
    @GET("producto/{id}")
    Call<List<Videojuego>> getPost(@Path("id") int id);
    @GET("cliente")
    Call<List<Cliente>> getPostClientes();
    @GET("obtenerLogins")
    Call<List<Login>> getPostLogins();
    @GET("getLoginInicio/{Usuario}")
    Call<List<Login>> getLoginiInicioId(@Path("Usuario") String Usuario);
    @POST("exportar")
    Call<Exportar> createPost(@Body Exportar exportar);
    @POST("detalleGuardar")
    Call<FacturaDetallev2> createDetalle(@Body FacturaDetallev2 factura  );
    @POST("crearcliente")
    Call<Cliente> createCliente(@Body Cliente cliente);
    @POST("LoginInicio")
    Call<Login> createLoginInicio(@Body Login login);
    @POST("login")
    Call<Login> createPostLogin(@Body Login login);
    @GET("exportar")
    Call<List<Venta>> getPostVenta();
    @GET("total")
    Call<List<Totales>> getPostTotales();
    @GET("exportar/{id}")
    Call<Venta> getPostVentaOne(@Path("id") int id);
    @DELETE("eliminar/{id}")
    Call<Venta> deleteVentaOne(@Path("id") int id);
    @PUT("exportarV/{id}")
    Call<ExportarVenta> updateVentaOne(@Path("id") int id, @Body ExportarVenta exportar);
    @PUT("LoginI/{Usuario}")
    Call<Usuario> updateContraUsuario(@Path("Usuario") String Usuario, @Body Usuario user);
    @PUT("productoactualizar/{id}")
    Call<Producto> updateVideojuegoStock(@Path("id") int id, @Body Producto videojuego);
    @POST("facturaT/{cedula}")
    Call<facturaCabecera> FacturaTransaccion(@Path("cedula") String cedula, @Body facturaCabecera vector);
    @DELETE("borrart")
    Call<Exportar> borratCarrito();
    @GET("facturar")
    Call<List<Factura>> ObtnerUltimaFactura();
    @GET("listafacturaMovil/{cedula}")
    Call<List<Factura>> getCompras(@Path("cedula") String cedula);

    @GET("getFactura/{id}")
    Call<List<FacturaVenta>> getComprasId(@Path("id") int id);
    @GET("getTotal/{id}")
    Call<Totales> getTotalId(@Path("id") int id);

    @GET("productostotales")
    Call<List<Videojuego>> getPostProductosTotales();
    @GET("listarClientes/{cedula}")
    Call<Cliente> listaClientesId(@Path("cedula") String cedula);
    @PUT("clienteactualizar/{cedula}")
    Call<ClienteLogin> ActualizarCliente(@Path("cedula") String cedula, @Body ClienteLogin cliente);


}
