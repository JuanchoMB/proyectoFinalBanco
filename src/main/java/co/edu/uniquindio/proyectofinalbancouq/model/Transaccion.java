package co.edu.uniquindio.proyectofinalbancouq.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaccion implements Serializable {


    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private String idTransaccion;
    private double monto;
    private TipoTransaccion tipoTransaccion;
    private LocalDateTime fecha;
    private String descripcion; // Descripción opcional
    private Cuenta cuentaOrigen; // Cuenta de donde se retira el dinero (solo para retiros y transferencias)
    private Cuenta cuentaDestino; // Cuenta a donde se deposita el dinero (solo para depósitos y transferencias)
    private Categoria categoria; // Categoría opcional para organizar la transacción

    // Constructor para Depósitos y Retiros
    public Transaccion(Usuario usuario String idTransaccion, double monto, TipoTransaccion tipoTransaccion, Cuenta cuentaOrigen, Cuenta cuentaDestino, String descripcion, Categoria categoria) {
        this.usuario=usuario;
        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = LocalDateTime.now();
        this.descripcion = descripcion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.categoria = categoria;
    }
    public Transaccion(){}
    // Getters y Setters
    public String getIdTransaccion() {
        return idTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return idTransaccion + " - " + tipoTransaccion + ": " + monto + " - " + fecha.toString() + " - " + descripcion;
    }

}
