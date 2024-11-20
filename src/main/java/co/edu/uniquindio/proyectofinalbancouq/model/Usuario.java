package co.edu.uniquindio.proyectofinalbancouq.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
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

    public void setCuentasAsociadas(LinkedList<Cuenta> cuentasAsociadas) {
        this.cuentasAsociadas = cuentasAsociadas;
    }

    private String id;
    private String nombreCompleto;
    private String correo;
    private String direccion;
    private String telefono;
    private String contraseña;
    private double saldo;
    private List<Transaccion> transacciones;
    private LinkedList<Cuenta> cuentasAsociadas;

    public Usuario(String id, String nombreCompleto, String correo, String direccion, String telefono, String contraseña) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.saldo = 0.0;
        this.transacciones = new ArrayList<>();
        this.cuentasAsociadas = new LinkedList<>();
    }

    public Usuario() {
        this.transacciones = new ArrayList<>();
        this.cuentasAsociadas = new LinkedList<>();
    }

    public void depositar(double monto, String descripcion, Cuenta cuentaDestino, Categoria categoria) {
        Transaccion transaccion = new Transaccion(this, "DEP-" + System.currentTimeMillis(), monto, TipoTransaccion.DEPOSITO, null, cuentaDestino, descripcion, categoria);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        this.saldo += monto;
        transacciones.add(transaccion);
    }

    public boolean retirar(double monto, String descripcion, Cuenta cuentaOrigen, Categoria categoria) {
        if (cuentaOrigen.getSaldo() >= monto) {
            Transaccion transaccion = new Transaccion(this, "RET-" + System.currentTimeMillis(), monto, TipoTransaccion.RETIRO, cuentaOrigen, null, descripcion, categoria);
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
            this.saldo -= monto;
            transacciones.add(transaccion);
            return true;
        }
        return false;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void añadirCuenta(Cuenta cuenta) {
        cuentasAsociadas.add(cuenta);
        this.saldo += cuenta.getSaldo();
    }

    public double getSaldo() {
        return saldo;
    }

    public LinkedList<Cuenta> getCuentasAsociadas() {
        return cuentasAsociadas;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}