package co.edu.uniquindio.proyectofinalbancouq.model;

import java.time.LocalDateTime;

public class Transaccion {
    private String idTransaccion;
    private double cantidad;
    private TipoTransaccion tipoTransaccion;
    private LocalDateTime fecha;

    public Transaccion(String idTransaccion, double cantidad, TipoTransaccion tipoTransaccion, Object o) {
        this.idTransaccion = idTransaccion;
        this.cantidad = cantidad;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = LocalDateTime.now();
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public TipoTransaccion getTipo() {
        return tipoTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return idTransaccion + " - " + tipoTransaccion + ": " + cantidad + " - " + fecha.toString();
    }
}
