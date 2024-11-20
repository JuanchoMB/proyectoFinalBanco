package co.edu.uniquindio.proyectofinalbancouq.model;

import co.edu.uniquindio.proyectofinalbancouq.util.TransaccionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nombreCompleto;
    private String correo;
    private String direccion;
    private String telefono;
    private String contraseña;
    private double saldo;
    private List<Transaccion> transacciones;

    public Usuario(String id, String nombreCompleto, String correo, String direccion, String telefono, String contraseña) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.saldo = 100.000;
        this.transacciones = new ArrayList<>();
    }

    public Usuario(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double cantidad, String descripcion, Cuenta cuentaDestino, Categoria categoria) {
        if (cantidad > 0) {
            saldo += cantidad;

            // Crear nueva transacción de depósito
            String idTransaccion = "DEP-" + System.currentTimeMillis();
            Transaccion transaccion = new Transaccion(usuario, idTransaccion, cantidad, TipoTransaccion.DEPOSITO, null, cuentaDestino, descripcion, categoria);

            // Añadir la transacción a la lista del usuario
            transacciones.add(transaccion);

            // Registrar la transacción
            TransaccionUtil.guardarTransaccion(transaccion);
        }
    }

    // Método para realizar un retiro
    public boolean retirar(double cantidad, String descripcion, Cuenta cuentaOrigen, Categoria categoria) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;

            // Crear nueva transacción de retiro
            String idTransaccion = "RET-" + System.currentTimeMillis();
            Transaccion transaccion = new Transaccion(idTransaccion, cantidad, TipoTransaccion.RETIRO, cuentaOrigen, null, descripcion, categoria);

            // Añadir la transacción a la lista del usuario
            transacciones.add(transaccion);

            // Registrar la transacción
            TransaccionUtil.guardarTransaccion(transaccion);

            return true;
        } else {
            System.out.println("Saldo insuficiente para el retiro.");
            return false; // Saldo insuficiente
        }
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombreCompleto+ '\'' +
                ", id='" + id + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

