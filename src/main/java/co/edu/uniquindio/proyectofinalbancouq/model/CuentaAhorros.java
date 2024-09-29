package co.edu.uniquindio.proyectofinalbancouq.model;


public class CuentaAhorros extends Cuenta {
    private static final long serialVersionUID = 1L;

    private double tasaInteres;

    public CuentaAhorros(String numeroCuenta, double saldoInicial, double tasaInteres) {
        super(numeroCuenta, saldoInicial);
        this.tasaInteres = tasaInteres;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
    }

    @Override
    public void retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
        } else {
            System.out.println("Fondos insuficientes");
        }
    }

    public void aplicarInteres() {
        saldo += saldo * tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }
}