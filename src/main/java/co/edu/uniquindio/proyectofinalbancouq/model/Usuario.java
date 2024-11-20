package co.edu.uniquindio.proyectofinalbancouq.model;

import co.edu.uniquindio.proyectofinalbancouq.util.TransaccionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
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
    private LinkedList<Cuenta> cuentasAsociadas;

    public Usuario(String id, String nombreCompleto, String correo, String direccion, String telefono, String contraseña) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.saldo = saldo;
        this.transacciones = new ArrayList<>();
        this.cuentasAsociadas = new LinkedList<>();
    }

    public Usuario() {
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

    public LinkedList<Cuenta> getCuentasAsociadas() {
        return cuentasAsociadas;
    }

    public void setCuentasAsociadas(LinkedList<Cuenta> cuentasAsociadas) {
        this.cuentasAsociadas = cuentasAsociadas;
    }

    public LinkedList<Transaccion> getTransaccionesAsociadas() {
        return (LinkedList<Transaccion>) transacciones;
    }

    public void actualizarSaldoTotal() {
        double nuevoSaldoTotal = 0.0;
        // Recorremos todas las cuentas asociadas al usuario
        for (Cuenta cuenta : cuentasAsociadas) {
            nuevoSaldoTotal += cuenta.getSaldo();  // Sumamos los saldos de todas las cuentas
        }
        this.saldo = nuevoSaldoTotal;  // Actualizamos el saldo total
    }


    public void añadirTransaccion(Transaccion transaccion) {
        // Verificar que las cuentas de origen y destino no sean nulas
        if (transaccion.getCuentaOrigen() == null || transaccion.getCuentaDestino() == null) {
            throw new IllegalArgumentException("Una de las cuentas no está válida.");
        }
        setDireccion(" " + transaccion.getCuentaOrigen());
        // Agregar la transacción a la lista
        transacciones.add(transaccion);

        // Verificar si hay saldo suficiente en la cuenta de origen
        if (transaccion.getMonto() <= transaccion.getCuentaOrigen().getSaldo()) {
            // Actualizar el saldo de las cuentas
            transaccion.getCuentaOrigen().setSaldo(transaccion.getCuentaOrigen().getSaldo() - transaccion.getMonto());
            transaccion.getCuentaDestino().setSaldo(transaccion.getCuentaDestino().getSaldo() + transaccion.getMonto());

            setSaldo(getSaldo() - transaccion.getMonto());
            // Actualizar el saldo total del usuario (si corresponde)
            actualizarSaldoTotal();
        }}

        public void añadirCuenta (Cuenta cuenta){
            cuentasAsociadas.add(cuenta);
            this.saldo += cuenta.getSaldo();
        }


        public List<Transaccion> getTransacciones () {
            return transacciones;
        }

        public String toString () {
            return "Usuario{" +
                    "nombre='" + nombreCompleto + '\'' +
                    ", id='" + id + '\'' +
                    ", correo='" + correo + '\'' +
                    ", direccion='" + direccion + '\'' +
                    ", telefono='" + telefono + '\'' +
                    '}';
        }

    }


