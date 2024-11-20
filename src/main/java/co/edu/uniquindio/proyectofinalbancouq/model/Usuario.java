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

            setSaldoTotal(getSaldoTotal() - transaccion.getMonto());
            // Actualizar el saldo total del usuario (si corresponde)
            actualizarSaldoTotal();
        }
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public String toString() {
        return "Usuario{" +
                "nombre='" + nombreCompleto + '\'' +
                ", id='" + id + '\'' +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
}

