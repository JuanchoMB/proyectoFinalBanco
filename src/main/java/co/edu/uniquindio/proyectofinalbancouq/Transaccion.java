package co.edu.uniquindio.proyectofinalbancouq;

import java.time.LocalDateTime;

public class Transaccion {
    private double cantidad;
    private String tipo;
    private LocalDateTime fecha;

    public Transaccion(double cantidad, String tipo) {
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return tipo + ": " + cantidad + " - " + fecha.toString();
    }
}
