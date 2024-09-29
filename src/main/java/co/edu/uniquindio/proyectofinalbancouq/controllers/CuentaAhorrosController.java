package co.edu.uniquindio.proyectofinalbancouq.controllers;


import co.edu.uniquindio.proyectofinalbancouq.model.CuentaAhorros;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class CuentaAhorrosController {

    @FXML
    private TextField numeroCuenta;

    @FXML
    private TextField saldoCuenta;

    @FXML
    private TextField tasaInteres;

    private CuentaAhorros cuentaAhorros;

    public void inicializar(CuentaAhorros cuentaAhorros) {
        this.cuentaAhorros = cuentaAhorros;
        numeroCuenta.setText(cuentaAhorros.getNumeroCuenta());
        saldoCuenta.setText(String.valueOf(cuentaAhorros.consultarSaldo()));
        tasaInteres.setText(String.valueOf(cuentaAhorros.getTasaInteres()));
    }

    @FXML
    public void aplicarInteres() {
        cuentaAhorros.aplicarInteres();
        saldoCuenta.setText(String.valueOf(cuentaAhorros.consultarSaldo()));
    }

    @FXML
    public void realizarDeposito(double monto) {
        cuentaAhorros.depositar(monto);
        saldoCuenta.setText(String.valueOf(cuentaAhorros.consultarSaldo()));
    }

    @FXML
    public void realizarRetiro(double monto) {
        cuentaAhorros.retirar(monto);
        saldoCuenta.setText(String.valueOf(cuentaAhorros.consultarSaldo()));
    }
}

