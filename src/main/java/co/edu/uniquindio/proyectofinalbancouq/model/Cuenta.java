package co.edu.uniquindio.proyectofinalbancouq.model;

import java.io.Serializable;

public abstract class Cuenta implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String numeroCuenta;
    protected double saldo;

    public Cuenta(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public abstract void depositar(double monto);

    public abstract void retirar(double monto);
}