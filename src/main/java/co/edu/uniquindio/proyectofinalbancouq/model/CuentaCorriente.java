package co.edu.uniquindio.proyectofinalbancouq.model;


public class CuentaCorriente extends Cuenta {
    private static final long serialVersionUID = 1L;

    private double sobregiro;

    public CuentaCorriente(String numeroCuenta, double saldoInicial, double sobregiro) {
        super(numeroCuenta, saldoInicial);
        this.sobregiro = sobregiro;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
    }

    @Override
    public void retirar(double monto) {
        if (saldo + sobregiro >= monto) {
            saldo -= monto;
        } else {
            System.out.println("Fondos insuficientes");
        }
    }

    public double getSobregiro() {
        return sobregiro;
    }
}