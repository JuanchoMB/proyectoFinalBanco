package co.edu.uniquindio.proyectofinalbancouq.model;

import java.util.ArrayList;
import java.util.List;


public class Usuario {
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
        this.saldo = 0.0;
        this.transacciones = new ArrayList<>();
    }

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

    public void depositar(double cantidad) {
        saldo += cantidad;
        String idTransaccion = "DEP-" + System.currentTimeMillis(); // Generar un ID único para la transacción
        Transaccion nuevaTransaccion = new Transaccion(idTransaccion, cantidad, TipoTransaccion.DEPOSITO, null);
        transacciones.add(nuevaTransaccion);
    }

    public void retirar(double cantidad) {
        if (cantidad <= saldo) {
            saldo -= cantidad;
            String idTransaccion = "RET-" + System.currentTimeMillis(); // Generar un ID único para la transacción
            Transaccion nuevaTransaccion = new Transaccion(idTransaccion, cantidad, TipoTransaccion.RETIRO, null);
            transacciones.add(nuevaTransaccion);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro.");
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

