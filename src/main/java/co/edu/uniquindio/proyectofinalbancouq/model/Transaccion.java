package co.edu.uniquindio.proyectofinalbancouq.model;

import java.time.LocalDateTime;

public class Transaccion {
    private String idTransaccion;
    private double cantidad;
    private TipoTransferencia tipoTransferencia;
    private LocalDateTime fecha;

    public Transaccion(double cantidad, String tipo) {
        this.cantidad = cantidad;
        this.tipoTransferencia = tipoTransferencia;
        this.fecha = LocalDateTime.now();
    }

    public double getCantidad() {
        return cantidad;
    }

    public TipoTransferencia getTipo() {
        return tipoTransferencia;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return tipoTransferencia + ": " + cantidad + " - " + fecha.toString();
    }
}
